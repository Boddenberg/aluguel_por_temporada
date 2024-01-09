package juninwins.project.model.accommodation

import jakarta.persistence.Entity
import jakarta.persistence.Id
import juninwins.project.model.accommodation.amenities.*

@Entity
data class Amenities(

        @Id
        val id: Long? = null,
        val basics: MutableList<Basics>?,
        val bathroom: MutableList<Bathroom>?,
        val bedroom: MutableList<Bedroom>?,
        val checkinAndCheckout: MutableList<CheckinAndCheckout>?,
        val diningRoom: MutableList<DiningRoom>?,
        val family: MutableList<Family>?,
        val internet: MutableList<Internet>?,
        val laundry: MutableList<Laundry>?,
        val location: MutableList<Location>?,
        val office: MutableList<Office>?,
        val outdoors: MutableList<Outdoors>?,
        val parking: MutableList<Parking>?,
        val security: MutableList<Security>?,
        val services: MutableList<Services>?
)