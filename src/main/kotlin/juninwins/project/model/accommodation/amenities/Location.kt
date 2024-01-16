package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Location(
        var lakeAccess: Boolean?,
        var resortAccess: Boolean?,
        var beachAccess: Boolean?,
        var privateEntrance: Boolean?,
        var skiInOutAccess: Boolean?,
        var nearbyLaundry: Boolean?,
        var waterView: Boolean?
)