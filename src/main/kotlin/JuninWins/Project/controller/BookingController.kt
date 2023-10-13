package JuninWins.Project.controller

import JuninWins.Project.DTO.BookingRequestDTO
import JuninWins.Project.model.Booking
import JuninWins.Project.service.BookingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bookings")
class BookingController(val bookingService: BookingService) {

    @GetMapping("search/booking")
    fun findBooking(@RequestHeader id: Long): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.findBookingById(id))
    }

    @PostMapping("register/booking")
    fun saveBooking(
        @RequestBody booking: BookingRequestDTO,
        @RequestHeader(name = "AccommodationId") id: Long,
        @RequestHeader(name = "GuestCPF") cpf: String
    ): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.save(booking, cpf, id))
    }
}