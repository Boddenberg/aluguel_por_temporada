package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Location(
        @get:DynamoDbAttribute("lakeAccess")
        var lakeAccess: Boolean? = null,

        @get:DynamoDbAttribute("resortAccess")
        var resortAccess: Boolean? = null,

        @get:DynamoDbAttribute("beachAccess")
        var beachAccess: Boolean? = null,

        @get:DynamoDbAttribute("privateEntrance")
        var privateEntrance: Boolean? = null,

        @get:DynamoDbAttribute("skiInOutAccess")
        var skiInOutAccess: Boolean? = null,

        @get:DynamoDbAttribute("nearbyLaundry")
        var nearbyLaundry: Boolean? = null,

        @get:DynamoDbAttribute("waterView")
        var waterView: Boolean? = null
)
