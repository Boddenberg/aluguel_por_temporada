package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import juninwins.project.exceptions.accommodation.AccommodationAlreadyRegisteredException
import juninwins.project.exceptions.guest.GuestAlreadyRegisteredException
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.model.guest.Guest
import juninwins.project.service.AccommodationService
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import java.util.UUID


@Service
class AccommodationServiceImpl(
    private val dynamoDbClient: DynamoDbClient
) : AccommodationService {

    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun saveAccommodation(accommodation: Accommodation): Accommodation {

        val accommodationKey = generateAccommodationKey(accommodation.id)

        if (isAccommodationKeyRegistered(accommodationKey)) {
            throw AccommodationAlreadyRegisteredException(accommodationKey)
        }
        accommodation.id = accommodationKey
        dynamoDbClient.putItem(createPutItemRequest(accommodation))
        return accommodation
    }

    override fun findAccommodationById(id: String): Accommodation {
        TODO("Not yet implemented")
    }

    override fun findAccommodationByCPF(cpf: String): List<Accommodation> {
        TODO("Not yet implemented")
    }

    override fun findAllAccommodations(): List<Accommodation> {
        TODO("Not yet implemented")
    }

    override fun updateAccommodation(guestDTO: UpdateGuestDTO): Accommodation {
        TODO("Not yet implemented")
    }

    override fun deleteAccommodationByCPF(cpf: String) {
        TODO("Not yet implemented")
    }

    private fun generateAccommodationKey(cpf: String): String {
        return  cpf + "#" + UUID.randomUUID();
    }

    private fun isAccommodationKeyRegistered(key: String): Boolean {
        val getKey = mapOf("id" to AttributeValue.builder().s(key).build())
        val getItemRequest = GetItemRequest.builder().tableName(Accommodation::class.simpleName).key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)
        return response.hasItem()
    }

    private fun createPutItemRequest(accommodation: Accommodation): PutItemRequest {
        val itemMap = mapper.convertValue(accommodation, Map::class.java) as Map<String, Any>
        val attributeValueMap = itemMap.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }

        return PutItemRequest.builder()
            .tableName(Guest::class.simpleName)
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
            else -> throw IllegalArgumentException("Tipo de atributo não suportado: $this")
        }
    }


}