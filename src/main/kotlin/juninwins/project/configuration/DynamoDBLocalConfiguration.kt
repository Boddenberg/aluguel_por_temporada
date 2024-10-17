package juninwins.project.configuration

import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.review.Review
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
class DynamoDBLocalConfiguration {

    @Bean
    fun createLocalDynamoDbTables(dynamoDbEnhancedClient : DynamoDbEnhancedClient) : Boolean {
        try {
            dynamoDbEnhancedClient.table(Review::class.java.simpleName, TableSchema.fromClass(Review::class.java)).createTable()
            println("Criando tabela: ${Review::class.java.simpleName}")
        } catch (ex: Exception) {
            println("Tabela ${Review::class.java.simpleName} já criada")
        }
        try {
            dynamoDbEnhancedClient.table(GuestComplete::class.java.simpleName, TableSchema.fromClass(GuestComplete::class.java)).createTable()
            println("Criando tabela: ${GuestComplete::class.java.simpleName}")
        } catch (ex: Exception) {
            println("Tabela ${GuestComplete::class.java.simpleName} já criada")
        }



//         try {
//            dynamoDbEnhancedClient.table(Address::class.java.simpleName, TableSchema.fromClass(Address::class.java)).createTable()
//            println("Criando tabela: ${Address::class.java.simpleName}")
//         } catch (ex: Exception) {
//            println("Tabela ${Address::class.java.simpleName} já criada")
//         }

        return true
    }
}