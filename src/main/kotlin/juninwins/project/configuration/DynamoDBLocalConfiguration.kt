package juninwins.project.configuration

import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.booking.Booking
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
    fun createLocalDynamoDbTables(dynamoDbEnhancedClient: DynamoDbEnhancedClient): Boolean {
        val tables = listOf(
            Accommodation::class.java,
            Guest::class.java,
            Booking::class.java
        )

        println("===== Iniciando criação de tabelas DynamoDB =====")

        tables.forEach { tableClass ->
            val tableName = tableClass.simpleName
            try {
                println("Tentando criar a tabela: $tableName...")
                val table = dynamoDbEnhancedClient.table(tableName, TableSchema.fromClass(tableClass))
                table.createTable()
                println("✅ Tabela '$tableName' criada com sucesso.")
            } catch (ex: Exception) {
                println("⚠️ Erro ao criar a tabela '$tableName': ${ex.message}")
                println("ℹ️ Verifique se a tabela '$tableName' já existe ou se há problemas de configuração.")
            }
        }

        println("===== Processo de criação de tabelas concluído =====")
        return true
    }
}
