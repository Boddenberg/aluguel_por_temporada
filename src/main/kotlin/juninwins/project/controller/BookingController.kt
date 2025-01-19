package juninwins.project.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.booking.Booking
import juninwins.project.model.booking.DTO.UpdateBookingDTO
import juninwins.project.service.BookingService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bookings")
@Validated
class BookingController(val bookingService: BookingService) {

    @GetMapping("/search/{id}")
    @Operation(summary = "Find a booking by ID")
    fun findBookingById(@PathVariable(name = "id") bookingId: String): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.findBookingById(bookingId))
    }

    @GetMapping("/searchAll")
    @Operation(summary = "Find all bookings")
    fun findAllBookings(): List<Booking> {
        return bookingService.findAllBookings()
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new booking")
    fun saveBooking(@RequestBody @Valid booking: Booking): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.saveBooking(booking))
    }

    @PutMapping("/update")
    @Operation(summary = "Update a booking")
    fun updateBooking(@RequestBody @Valid booking: UpdateBookingDTO): ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.updateBooking(booking))
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a booking by ID")
    fun deleteBooking(@PathVariable(name = "id") bookingId: String): ResponseEntity<Void> {
        bookingService.deleteBookingById(bookingId)
        return ResponseEntity.ok().build()
    }
}
