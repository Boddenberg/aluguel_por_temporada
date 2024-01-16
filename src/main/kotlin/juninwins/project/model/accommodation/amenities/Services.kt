package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Services(
        var breakfast: Boolean?,
        var longTermStaysAllowed: Boolean?,
        var cleaningDuringStay: Boolean?,
        var luggageDropoffAllowed: Boolean?
)