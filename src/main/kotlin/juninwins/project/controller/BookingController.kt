package juninwins.project.controller

import juninwins.project.DTO.BookingRequestDTO
import juninwins.project.model.booking.Booking
import juninwins.project.service.BookingService
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
        @RequestHeader(name = "HostCPF") hostCPF: String,
        @RequestHeader(name = "GuestCPF") guestCPF : String
    ): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.save(booking, hostCPF, guestCPF, id))
    }

}