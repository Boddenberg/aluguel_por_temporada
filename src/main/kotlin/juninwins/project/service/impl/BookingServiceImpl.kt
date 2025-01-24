package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import juninwins.project.exceptions.booking.BookingNotFoundException
import juninwins.project.model.booking.Booking
import juninwins.project.model.booking.DTO.UpdateBookingDTO
import juninwins.project.service.BookingService
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

@Service
class BookingServiceImpl(
    private val dynamoDbClient: DynamoDbClient
) : BookingService {
//TODO: APLICAR REGRAS DE DATAS DE RESERVAS
//TODO: APLICAR REGRAS DE PREÇO
//TODO: APLICAR POLITICAS DE DESCONTO
    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun saveBooking(booking: Booking): Booking {
        if (isBookingRegistered(booking.id)) {
            throw BookingNotFoundException(booking.id)
        }
        dynamoDbClient.putItem(createPutItemRequest(booking))
        return booking
    }

    override fun findBookingById(id: String): Booking {
        return findById(id) ?: throw BookingNotFoundException(id)
    }

    override fun findAllBookings(): List<Booking> {
        val scanRequest = ScanRequest.builder()
            .tableName(Booking::class.simpleName)
            .build()

        val scanResponse = dynamoDbClient.scan(scanRequest)
        return scanResponse.items().map { item ->
            mapper.convertValue(item.mapValues { it.value.toAttributeValue() }, Booking::class.java)
        }
    }

    override fun updateBooking(updatedBooking: UpdateBookingDTO): Booking {
        val existingBooking = findById(updatedBooking.id) ?: throw BookingNotFoundException(updatedBooking.id)
        val mergedBooking = mergeBooking(existingBooking, updatedBooking)

        dynamoDbClient.putItem(createPutItemRequest(mergedBooking))
        return mergedBooking
    }

    override fun deleteBookingById(id: String) {
        if (!isBookingRegistered(id)) {
            throw BookingNotFoundException(id)
        }

        val deleteKey = mapOf("id" to AttributeValue.builder().s(id).build())
        val deleteItemRequest = DeleteItemRequest.builder()
            .tableName(Booking::class.simpleName)
            .key(deleteKey)
            .build()

        dynamoDbClient.deleteItem(deleteItemRequest)
    }

    private fun findById(id: String): Booking? {
        val getKey = mapOf("id" to AttributeValue.builder().s(id).build())
        val getItemRequest = GetItemRequest.builder().tableName(Booking::class.simpleName).key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)

        return if (response.hasItem()) {
            mapper.convertValue(response.item().mapValues { it.value.toAttributeValue() }, Booking::class.java)
        } else null
    }

    private fun isBookingRegistered(id: String): Boolean {
        val getKey = mapOf("id" to AttributeValue.builder().s(id).build())
        val getItemRequest = GetItemRequest.builder().tableName(Booking::class.simpleName).key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)
        return response.hasItem()
    }

    private fun mergeBooking(existingBooking: Booking, updatedBookingDTO: UpdateBookingDTO): Booking {
        return existingBooking.copy(
            accommodation = updatedBookingDTO.accommodation ?: existingBooking.accommodation,
            startDate = updatedBookingDTO.startDate ?: existingBooking.startDate,
            endDate = updatedBookingDTO.endDate ?: existingBooking.endDate,
            bookingDuration = updatedBookingDTO.bookingDuration ?: existingBooking.bookingDuration,
            totalPrice = updatedBookingDTO.totalPrice ?: existingBooking.totalPrice,
            reviwedByGuest = updatedBookingDTO.reviwedByGuest ?: existingBooking.reviwedByGuest,
            reviwedByHost = updatedBookingDTO.reviwedByHost ?: existingBooking.reviwedByHost,
            guest = updatedBookingDTO.guest ?: existingBooking.guest,
            host = updatedBookingDTO.host ?: existingBooking.host,
            status = updatedBookingDTO.status ?: existingBooking.status,
            reviewStatus = updatedBookingDTO.reviewStatus ?: existingBooking.reviewStatus
        )
    }

    private fun createPutItemRequest(booking: Booking): PutItemRequest {
        val itemMap = mapper.convertValue(booking, Map::class.java) as Map<String, Any>
        val attributeValueMap = itemMap.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }

        return PutItemRequest.builder()
            .tableName(Booking::class.simpleName)
            .item(attributeValueMap)
            .build()
    }

    private fun Map<String, *>.toAttributeValueMap(): Map<String, AttributeValue> {
        return this.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }
    }

    private fun AttributeValue.toAttributeValue(): Any {
        return when {
            this.s() != null -> this.s()
            this.bool() != null -> this.bool()
            this.m() != null -> this.m().mapValues { it.value.toAttributeValue() }
            else -> throw IllegalArgumentException("Unsupported attribute type: $this")
        }
    }
}
