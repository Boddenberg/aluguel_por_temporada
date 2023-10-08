package JuninWins.Project.service

import JuninWins.Project.DTO.BookingRequestDTO
import JuninWins.Project.model.Booking
import org.springframework.http.ResponseEntity

interface BookingService {

    fun save(booking: BookingRequestDTO, cpf: String, id: Long) : Booking

    fun findBookingById(id: Long) : Booking

    fun update(id: Long, newBooking: Booking) : Booking

    fun deleteById(id : Long) : ResponseEntity<String>

}