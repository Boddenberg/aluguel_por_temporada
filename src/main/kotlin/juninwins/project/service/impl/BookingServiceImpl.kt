package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import juninwins.project.exceptions.booking.BookingNotFoundException
import juninwins.project.model.booking.Booking
import juninwins.project.service.BookingService
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

@Service
class BookingServiceImpl(
    private val dynamoDbClient: DynamoDbClient
) : BookingService {

    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun saveBooking(booking: Booking): Booking {
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

    override fun updateBooking(id: String, updatedBooking: Booking): Booking {
        val existingBooking = findById(id) ?: throw BookingNotFoundException(id)
        val mergedBooking = mergeBooking(existingBooking, updatedBooking)
        dynamoDbClient.putItem(createPutItemRequest(mergedBooking))
        return mergedBooking
    }

    override fun deleteBookingById(id: String) {
        if (findById(id) == null) {
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

    private fun mergeBooking(existingBooking: Booking, updatedBooking: Booking): Booking {
        return existingBooking.copy(
            accommodation = updatedBooking.accommodation ?: existingBooking.accommodation,
            startDate = updatedBooking.startDate ?: existingBooking.startDate,
            endDate = updatedBooking.endDate ?: existingBooking.endDate,
            bookingDuration = updatedBooking.bookingDuration.takeIf { it > 0 } ?: existingBooking.bookingDuration,
            totalPrice = updatedBooking.totalPrice.takeIf { it > 0 } ?: existingBooking.totalPrice,
            reviwedByGuest = updatedBooking.reviwedByGuest ?: existingBooking.reviwedByGuest,
            reviwedByHost = updatedBooking.reviwedByHost ?: existingBooking.reviwedByHost,
            guest = updatedBooking.guest ?: existingBooking.guest,
            host = updatedBooking.host ?: existingBooking.host,
            status = updatedBooking.status ?: existingBooking.status,
            reviewStatus = updatedBooking.reviewStatus ?: existingBooking.reviewStatus
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
