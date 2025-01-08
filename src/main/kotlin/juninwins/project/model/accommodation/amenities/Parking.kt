package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Parking(
        @get:DynamoDbAttribute("electricVehicleCharger")
        var electricVehicleCharger: Boolean? = null,

        @get:DynamoDbAttribute("groundLevelAccess")
        var groundLevelAccess: Boolean? = null,

        @get:DynamoDbAttribute("freeStreetParking")
        var freeStreetParking: Boolean? = null,

        @get:DynamoDbAttribute("parkingIncluded")
        var parkingIncluded: Boolean? = null,

        @get:DynamoDbAttribute("paidParkingOffPremises")
        var paidParkingOffPremises: Boolean? = null,

        @get:DynamoDbAttribute("paidParkingOnPremises")
        var paidParkingOnPremises: Boolean? = null
)
