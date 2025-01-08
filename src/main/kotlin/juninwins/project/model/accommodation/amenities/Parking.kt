package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Parking(
        @get:DynamoDbAttribute("electricVehicleCharger")
        var electricVehicleCharger: Boolean?,

        @get:DynamoDbAttribute("groundLevelAccess")
        var groundLevelAccess: Boolean?,

        @get:DynamoDbAttribute("freeStreetParking")
        var freeStreetParking: Boolean?,

        @get:DynamoDbAttribute("parkingIncluded")
        var parkingIncluded: Boolean?,

        @get:DynamoDbAttribute("paidParkingOffPremises")
        var paidParkingOffPremises: Boolean?,

        @get:DynamoDbAttribute("paidParkingOnPremises")
        var paidParkingOnPremises: Boolean?
)