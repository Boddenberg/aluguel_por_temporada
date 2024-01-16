package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Office(
        var dedicatedWorkspace: Boolean?,
        var wifi: Boolean?,
        var portableWifi: Boolean?,
        var printer: Boolean?
)