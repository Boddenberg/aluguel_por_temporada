package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import juninwins.project.model.accommodation.amenities.*


@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Amenities(
        val basics: Basics?,
        val bathroom: Bathroom?,
        val bedroom: Bedroom?,
        val entertainment: Entertainment?,
        val family: Family?,
        val diningRoom: Kitchen?,
        val laundry: Laundry?,
        val location: Location?,
        val office: Office?,
        val parking: Parking?,
        val security: Security?,
        val services: Services?,
        val streaming: Streaming?
)

