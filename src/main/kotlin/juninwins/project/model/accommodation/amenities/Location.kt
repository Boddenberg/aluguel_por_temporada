package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Location(
        @get:DynamoDbAttribute("lakeAccess")
        var lakeAccess: Boolean?,

        @get:DynamoDbAttribute("resortAccess")
        var resortAccess: Boolean?,

        @get:DynamoDbAttribute("beachAccess")
        var beachAccess: Boolean?,

        @get:DynamoDbAttribute("privateEntrance")
        var privateEntrance: Boolean?,

        @get:DynamoDbAttribute("skiInOutAccess")
        var skiInOutAccess: Boolean?,

        @get:DynamoDbAttribute("nearbyLaundry")
        var nearbyLaundry: Boolean?,

        @get:DynamoDbAttribute("waterView")
        var waterView: Boolean?
)
