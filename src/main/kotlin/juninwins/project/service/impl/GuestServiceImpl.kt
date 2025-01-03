package juninwins.project.service.impl

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.review.Review
import juninwins.project.service.GuestService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest
import java.util.*

@Service
class GuestServiceImpl (
    val dynamoDbTemplate: DynamoDbTemplate,
    val dynamoDbEnhancedClient : DynamoDbEnhancedClient,
) : GuestService {

    override fun save(customer: GuestComplete): GuestComplete {

        return dynamoDbTemplate.save(customer)
    }

    override fun findGuestByCPF(cpf: String) : GuestComplete {
        return findByCPF(cpf)
    }

    override fun findAllGuests(): List<GuestComplete> {
        val table = dynamoDbEnhancedClient.table("GuestComplete", TableSchema.fromBean(GuestComplete::class.java))
        val scanRequest = ScanEnhancedRequest.builder()
            .build()

        val scanIterator = table.scan(scanRequest)

        return scanIterator.items().toList()
    }

    private fun findByCPF(cpf: String): GuestComplete {
        return Optional.ofNullable(dynamoDbTemplate.load(Key.builder().partitionValue(cpf).build(), GuestComplete::class.java)).orElseThrow { Exception("Guest CPF not found!")}
    }



}