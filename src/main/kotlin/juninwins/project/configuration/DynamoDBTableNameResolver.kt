package juninwins.project.configuration

import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver
import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.review.Review
import org.springframework.stereotype.Component

@Component
class DynamoDBTableNameResolver : DynamoDbTableNameResolver {

    override fun <T : Any?> resolve(clazz: Class<T>): String {
        return when (clazz) {
            GuestComplete::class.java -> GuestComplete::class.java.simpleName
            Review::class.java -> Review::class.java.simpleName
            else -> throw IllegalArgumentException("poe a classe ai po: ${clazz.simpleName}")
        }
    }
}