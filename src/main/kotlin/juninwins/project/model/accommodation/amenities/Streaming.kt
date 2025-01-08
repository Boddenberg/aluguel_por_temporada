package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Streaming(
        @get:DynamoDbAttribute("primeVideo")
        var primeVideo: Boolean? = null,

        @get:DynamoDbAttribute("netflix")
        var netflix: Boolean? = null,

        @get:DynamoDbAttribute("hbo")
        var hbo: Boolean? = null,

        @get:DynamoDbAttribute("disneyPlus")
        var disneyPlus: Boolean? = null,

        @get:DynamoDbAttribute("globoplay")
        var globoplay: Boolean? = null,

        @get:DynamoDbAttribute("appleTvPlus")
        var appleTvPlus: Boolean? = null,

        @get:DynamoDbAttribute("paramountPlus")
        var paramountPlus: Boolean? = null,

        @get:DynamoDbAttribute("starPlus")
        var starPlus: Boolean? = null,

        @get:DynamoDbAttribute("crunchyroll")
        var crunchyroll: Boolean? = null
)
