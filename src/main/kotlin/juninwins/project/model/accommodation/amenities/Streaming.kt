package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Streaming(
        @get:DynamoDbAttribute("primeVideo")
        var primeVideo: Boolean?,

        @get:DynamoDbAttribute("netflix")
        var netflix: Boolean?,

        @get:DynamoDbAttribute("hbo")
        var hbo: Boolean?,

        @get:DynamoDbAttribute("disneyPlus")
        var disneyPlus: Boolean?,

        @get:DynamoDbAttribute("globoplay")
        var globoplay: Boolean?,

        @get:DynamoDbAttribute("appleTvPlus")
        var appleTvPlus: Boolean?,

        @get:DynamoDbAttribute("paramountPlus")
        var paramountPlus: Boolean?,

        @get:DynamoDbAttribute("starPlus")
        var starPlus: Boolean?,

        @get:DynamoDbAttribute("crunchyroll")
        var crunchyroll: Boolean?
)