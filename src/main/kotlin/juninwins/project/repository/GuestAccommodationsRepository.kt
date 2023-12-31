package juninwins.project.repository

import juninwins.project.model.Guest
import juninwins.project.model.GuestAccommodations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GuestAccommodationsRepository : JpaRepository<GuestAccommodations, Long> {

    fun findByGuest(guest: Guest): Optional<GuestAccommodations>
}