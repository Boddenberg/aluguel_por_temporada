package juninwins.project.model.accommodation.amenities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Basics (

        @Id
        val id: Long? = null,

        val centralHeating: Boolean? = false,

        val airConditioning: Boolean? = false,

        val kitchen: Boolean? = false,

        val washingMachine: Boolean? = false,

        val dryer: Boolean? = false,

        val tv: Boolean? = false,

        val wifi: Boolean? = false,

        val hotWater: Boolean? = false,
)
