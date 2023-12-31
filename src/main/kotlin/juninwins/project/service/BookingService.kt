package juninwins.project.service

import juninwins.project.DTO.BookingRequestDTO
import juninwins.project.model.Booking
import org.springframework.http.ResponseEntity

interface BookingService {

    fun save(booking: BookingRequestDTO, hostCPF: String, guestCPF: String, idAccommodation: Long) : Booking

    fun findBookingById(id: Long) : Booking

    fun update(id: Long, newBooking: Booking) : Booking

    fun deleteById(id : Long) : ResponseEntity<String>

}