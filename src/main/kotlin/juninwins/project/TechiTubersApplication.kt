package juninwins.project

import jakarta.annotation.PostConstruct
import juninwins.project.model.guest.Guest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import java.lang.System.Logger

@SpringBootApplication
class TechiTubersApplication


fun main(args: Array<String>) {
	runApplication<TechiTubersApplication>(*args)

}

