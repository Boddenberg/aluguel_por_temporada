package juninwins.project.configuration

import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.guest.Guest
import juninwins.project.model.review.Review
import org.springframework.stereotype.Component

@Component
class DynamoDBTableNameResolver : DynamoDbTableNameResolver {

    override fun <T : Any?> resolve(clazz: Class<T>): String {
        return when (clazz) {
            Guest::class.java -> Guest::class.java.simpleName
            Accommodation::class.java -> Accommodation::class.java.simpleName
            else -> throw IllegalArgumentException("poe a classe ai po: ${clazz.simpleName}")
        }
    }
}