package juninwins.project.model.accommodation.amenities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Basics (

        @Id
        val id: Long? = null,

        @Column(name = "central_heating")
        val centralHeating: Boolean = false,

        @Column(name = "air_conditioning")
        val airConditioning: Boolean = false,

        @Column(name = "kitchen")
        val kitchen: Boolean = false,

        @Column(name = "washing_machine")
        val washingMachine: Boolean = false,

        @Column(name = "dryer")
        val dryer: Boolean = false,

        @Column(name = "tv")
        val tv: Boolean = false,

        @Column(name = "wifi")
        val wifi: Boolean = false,

        @Column(name = "hot_water")
        val hotWater: Boolean = false
)
