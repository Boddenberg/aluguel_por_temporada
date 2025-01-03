package juninwins.project.service.impl

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

import java.util.*

@Service
class GuestServiceImpl (
    val dynamoDbTemplate: DynamoDbTemplate,
    val dynamoDbEnhancedClient : DynamoDbEnhancedClient,
) : GuestService {

    override fun save(customer: Guest): Guest {
//        dynamoDbTemplate.scan(ScanEnhancedRequest.builder().build(), Guest::class.java)

        val itemValues = mutableMapOf<String, AttributeValue>()


        return dynamoDbTemplate.save(customer)
    }

    override fun createItem(table: DynamoDbTable<Guest>, item: Guest) {
        table.putItem(item)
        println("Item inserido com sucesso!")
    }

    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
    }

    override fun findAllGuests(): List<Guest> {
        val table = dynamoDbEnhancedClient.table("GuestComplete", TableSchema.fromBean(Guest::class.java))
        val scanRequest = ScanEnhancedRequest.builder()
            .build()

        val scanIterator = table.scan(scanRequest)

        return scanIterator.items().toList()
    }

    private fun findByCPF(cpf: String): Guest {
        return Optional.ofNullable(dynamoDbTemplate.load(Key.builder().partitionValue(cpf).build(), Guest::class.java)).orElseThrow { Exception("Guest CPF not found!")}
    }



}