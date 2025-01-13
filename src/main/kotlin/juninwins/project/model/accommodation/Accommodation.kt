package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import juninwins.project.model.address.Address
import juninwins.project.model.review.ReviewByGuest
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey


@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean()
data class Accommodation(

        @get:DynamoDbPartitionKey
        @get:DynamoDbAttribute("id")
        @field:NotBlank(message = "Id é obrigatório")
        var id: String = "",

        @get:DynamoDbAttribute("type")
        @field:NotBlank(message = "Type é obrigatório")
        var type: String = "", // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc

        @get:DynamoDbAttribute("capacity")
        var capacity: Int = 0, // até quantas pessoas podem se hospedar na acomodação

        @get:DynamoDbAttribute("basePrice")
        var basePrice: Double? = null,

        @get:DynamoDbAttribute("amenities")
        var amenities: Amenities? = null,

        @get:DynamoDbAttribute("reviews")
        var reviews: MutableList<ReviewByGuest>? = null,

        @get:DynamoDbAttribute("address")
        var address: Address? = null
)
