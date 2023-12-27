package juninwins.project.repository

import juninwins.project.model.Accommodation
import juninwins.project.model.Booking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface BookingRepository : JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.accommodation = :accommodation " +
            "AND ((b.startDate <= :endDate AND b.endDate >= :startDate) " +
            "OR (b.startDate >= :startDate AND b.startDate <= :endDate))")
    fun findByAccommodationAndDateRange(accommodation: Accommodation, startDate: LocalDate, endDate: LocalDate): Booking?
}