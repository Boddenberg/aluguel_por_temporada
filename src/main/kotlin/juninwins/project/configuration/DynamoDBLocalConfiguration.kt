package juninwins.project.configuration

import juninwins.project.model.guest.Guest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
class DynamoDBLocalConfiguration {

    @Bean
    fun createLocalDynamoDbTables(dynamoDbEnhancedClient : DynamoDbEnhancedClient) : Boolean {
        try {
            dynamoDbEnhancedClient.table(Guest::class.java.simpleName, TableSchema.fromClass(Guest::class.java)).createTable()
            println("Criando tabela: ${Guest::class.java.simpleName}")
            return true
        } catch (ex: Exception) {
            println(ex.message)
            println(Guest::class.java.simpleName)
            println("Tabela já criada")
        }
        return true
    }
}