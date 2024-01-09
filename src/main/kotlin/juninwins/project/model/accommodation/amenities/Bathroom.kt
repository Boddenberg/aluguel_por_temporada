package juninwins.project.model.accommodation.amenities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Bathroom (

        @Id
        val id: Long? = null,

        @Column(name = "bathtub")
        val bathtub: Boolean = false,

        @Column(name = "bidet")
        val bidet: Boolean = false,

        @Column(name = "outdoor_shower")
        val outdoorShower: Boolean = false,

        @Column(name = "conditioner")
        val conditioner: Boolean = false,

        @Column(name = "shower_gel")
        val showerGel: Boolean = false,

        @Column(name = "cleaning_products")
        val cleaningProducts: Boolean = false,

        @Column(name = "body_soap")
        val bodySoap: Boolean = false,

        @Column(name = "hair_dryer")
        val hairDryer: Boolean = false,

        @Column(name = "shampoo")
        val shampoo: Boolean = false,

        @Column(name = "hot_water")
        val hotWater: Boolean = false
)
