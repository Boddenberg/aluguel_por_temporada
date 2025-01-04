package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

@Service
class GuestServiceImpl(
    private val dynamoDbEnhancedClient: DynamoDbEnhancedClient,
    private val dynamoDbClient: DynamoDbClient
) : GuestService {

    override fun save(customer: Guest): Guest {
        val mapper: ObjectMapper = jacksonObjectMapper()
        val itemMap = mapper.convertValue(customer, Map::class.java) as Map<String, Any>
        val attributeValueMap = itemMap.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }

        val putItemRequest = PutItemRequest.builder()
            .tableName("Guest")
            .item(attributeValueMap)
            .build()

        dynamoDbClient.putItem(putItemRequest)
        return customer
    }

    override fun findGuestByCPF(cpf: String): Guest {
        return findByCPF(cpf)
    }

    override fun findAllGuests(): List<Guest> {
        val scanRequest = software.amazon.awssdk.services.dynamodb.model.ScanRequest.builder()
            .tableName("Guest")
            .build()

        val scanResponse = dynamoDbClient.scan(scanRequest)
        val mapper: ObjectMapper = jacksonObjectMapper()

        return scanResponse.items().map { item ->
            val guestMap = item.mapValues { it.value.toAttributeValue() }
            mapper.convertValue(guestMap, Guest::class.java)
        }
    }

    override fun deleteGuestByCPF(cpf: String) {
        val deleteKey = mapOf("cpf" to AttributeValue.builder().s(cpf).build())
        val deleteItemRequest = DeleteItemRequest.builder()
            .tableName("Guest")
            .key(deleteKey)
            .build()

        dynamoDbClient.deleteItem(deleteItemRequest)
    }


    override fun updateGuest(guest: Guest): Guest {
        val mapper: ObjectMapper = jacksonObjectMapper()
        val itemMap = mapper.convertValue(guest, Map::class.java) as Map<String, Any>
        val attributeValueMap = itemMap.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }

        val putItemRequest = PutItemRequest.builder()
            .tableName("Guest")
            .item(attributeValueMap)
            .build()

        dynamoDbClient.putItem(putItemRequest)
        return guest
    }

    private fun findByCPF(cpf: String): Guest {
        val getKey = mapOf("cpf" to AttributeValue.builder().s(cpf).build())
        val getItemRequest = GetItemRequest.builder().tableName("Guest").key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)
        val itemMap = response.item().mapValues { it.value.toAttributeValue() }
        val mapper: ObjectMapper = jacksonObjectMapper()
        return mapper.convertValue(itemMap, Guest::class.java)
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
