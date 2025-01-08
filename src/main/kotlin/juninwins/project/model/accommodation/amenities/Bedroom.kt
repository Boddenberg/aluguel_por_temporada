package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bedroom(
        @get:DynamoDbAttribute("blackoutCurtains")
        var blackoutCurtains: Boolean? = null,

        @get:DynamoDbAttribute("extraBlanketsAndPillows")
        var extraBlanketsAndPillows: Boolean? = null,

        @get:DynamoDbAttribute("iron")
        var iron: Boolean? = null,

        @get:DynamoDbAttribute("clothesStorage")
        var clothesStorage: Boolean? = null,

        @get:DynamoDbAttribute("bedLinen")
        var bedLinen: Boolean? = null,

        @get:DynamoDbAttribute("mosquitoNet")
        var mosquitoNet: Boolean? = null,

        @get:DynamoDbAttribute("clothesDryingRack")
        var clothesDryingRack: Boolean? = null,

        @get:DynamoDbAttribute("safe")
        var safe: Boolean? = null
)
