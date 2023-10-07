package JuninWins.Project.service

import JuninWins.Project.model.Booking
import org.springframework.http.ResponseEntity

interface BookingService {

    fun save(booking: Booking) : Booking

    fun findBookingById(id: String) : Booking

    fun update(id: String, newBooking: Booking) : Booking

    fun deleteById(id : String) : ResponseEntity<String>

}