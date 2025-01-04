package juninwins.project.model.review

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.time.LocalDate
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean()
data class Review(

        @get:DynamoDbPartitionKey
        @get:DynamoDbAttribute("reviewId")
        var reviewId: String? = UUID.randomUUID().toString(),

        @get:DynamoDbAttribute("madeByCPF")
        var madeByCPF: String? = null,

        @get:DynamoDbAttribute("madeByName")
        var madeByName: String? = null,

        @get:DynamoDbAttribute("cleaningScore")
        var cleaningScore: Int? = null,

        @get:DynamoDbAttribute("cleaningComment")
        var cleaningComment: String? = null,

        @get:DynamoDbAttribute("comfortScore")
        var comfortScore: Int? = null,

        @get:DynamoDbAttribute("comfortComment")
        var comfortComment: String? = null,

        @get:DynamoDbAttribute("locationScore")
        var locationScore: Int? = null,

        @get:DynamoDbAttribute("locationComment")
        var locationComment: String? = null,

        @get:DynamoDbAttribute("hostScore")
        var hostScore: Int? = null,

        @get:DynamoDbAttribute("hostComment")
        var hostComment: String? = null,

        @get:DynamoDbAttribute("generalComment")
        var generalComment: String? = null,

        @get:DynamoDbAttribute("bookingDate")
        var bookingDate: String? = null,

        @get:DynamoDbAttribute("submissionDate")
        var submissionDate: LocalDate? = LocalDate.now()
)
