package juninwins.project.model.accommodation.amenities


import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Basics (
        val centralHeating: Boolean?,
        val generalAirConditioning: Boolean?,
        val dryer: Boolean?,
        val generalWifi: Boolean?
)
