package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bedroom(
        var blackoutCurtains: Boolean?,
        var extraBlanketsAndPillows: Boolean?,
        var iron: Boolean?,
        var clothesStorage: Boolean?,
        var bedLinen: Boolean?,
        var mosquitoNet: Boolean?,
        var clothesDryingRack: Boolean?,
        var safe: Boolean?
)