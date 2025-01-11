package juninwins.project.configuration

import juninwins.project.model.guest.Guest
import juninwins.project.model.review.Review
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
@Profile(value = ["local"])
class DynamoDBLocalConfiguration {

    @Bean
    fun createLocalDynamoDbTables(dynamoDbEnhancedClient : DynamoDbEnhancedClient) : Boolean {
        try {
            dynamoDbEnhancedClient.table(Guest::class.java.simpleName, TableSchema.fromClass(Guest::class.java)).createTable()
            println("Criando tabela: ${Guest::class.java.simpleName}")
        } catch (ex: Exception) {
            println(ex.message)
            println("Tabela ${Guest::class.java.simpleName} já criada")
        }
        return true
    }
}