package juninwins.project.model.guest

import jakarta.persistence.*
import juninwins.project.model.accommodation.Accommodation


data class HostAccommodations(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val guest: Guest,

    var accommodations: MutableList<Accommodation> = mutableListOf()
)
