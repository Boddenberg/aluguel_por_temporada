package JuninWins.Project.repository

import JuninWins.Project.model.Booking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository : JpaRepository<Booking, String> {
}