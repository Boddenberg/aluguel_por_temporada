package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable


@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Security(
        var carbonMonoxideAlarm: Boolean?,
        var smokeDetector: Boolean?,
        var fireExtinguisher: Boolean?,
        var firstAidKit: Boolean?
)