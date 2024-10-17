package juninwins.project.model.guest

import jakarta.persistence.*
import juninwins.project.model.accommodation.Accommodation


data class HostAccommodations(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val guestComplete: GuestComplete,

    var accommodations: MutableList<Accommodation> = mutableListOf()
)
