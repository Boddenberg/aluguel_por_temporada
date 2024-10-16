package juninwins.project.configuration

import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver
import juninwins.project.model.guest.Guest
import org.springframework.stereotype.Component

@Component
class DynamoDBTableNameResolver : DynamoDbTableNameResolver {

    override fun <T : Any?> resolve(clazz: Class<T>): String {
        return Guest::class.java.simpleName
    }


}