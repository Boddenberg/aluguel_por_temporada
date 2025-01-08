package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bedroom(
        @get:DynamoDbAttribute("blackoutCurtains")
        var blackoutCurtains: Boolean?,

        @get:DynamoDbAttribute("extraBlanketsAndPillows")
        var extraBlanketsAndPillows: Boolean?,

        @get:DynamoDbAttribute("iron")
        var iron: Boolean?,

        @get:DynamoDbAttribute("clothesStorage")
        var clothesStorage: Boolean?,

        @get:DynamoDbAttribute("bedLinen")
        var bedLinen: Boolean?,

        @get:DynamoDbAttribute("mosquitoNet")
        var mosquitoNet: Boolean?,

        @get:DynamoDbAttribute("clothesDryingRack")
        var clothesDryingRack: Boolean?,

        @get:DynamoDbAttribute("safe")
        var safe: Boolean?
)