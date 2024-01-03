package juninwins.project.controller

import com.mysql.cj.x.protobuf.Mysqlx.Ok
import juninwins.project.DTO.BookingRequestDTO
import juninwins.project.exceptions.BookingNotFoundException
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

    @DeleteMapping("{id}")
    fun deleteBooking(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        return try {
            bookingService.deleteById(id)
            ResponseEntity.noContent().build() // Retorna 204 No Content
        } catch (e: BookingNotFoundException) {
            ResponseEntity.notFound().build() // Retorna 404 Not Found
        }
        // Outras exceções podem ser tratadas de forma similar
    }

}