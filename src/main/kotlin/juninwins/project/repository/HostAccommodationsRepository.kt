package juninwins.project.repository

import juninwins.project.model.guest.Guest
import juninwins.project.model.guest.HostAccommodations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HostAccommodationsRepository : JpaRepository<HostAccommodations, Long> {

    fun findByGuest(guest: Guest): Optional<HostAccommodations>
}