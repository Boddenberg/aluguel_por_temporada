package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Parking(
        var electricVehicleCharger: Boolean?,
        var groundLevelAccess: Boolean?,
        var freeStreetParking: Boolean?,
        var parkingIncluded: Boolean?,
        var paidParkingOffPremises: Boolean?,
        var paidParkingOnPremises: Boolean?
)