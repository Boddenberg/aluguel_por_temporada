package JuninWins.Project.controller

import JuninWins.Project.DTO.BookingRequestDTO
import JuninWins.Project.model.Booking
import JuninWins.Project.repository.BookingRepository
import JuninWins.Project.service.AccommodationService
import JuninWins.Project.service.BookingService
import JuninWins.Project.service.GuestService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bookings")
class BookingController(val bookingService: BookingService) {

    @GetMapping("search/booking")
    fun findBooking(@RequestHeader id : Long) : ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.findBookingById(id))
    }

    @PostMapping("register/booking")
    fun saveBooking(@RequestBody booking: BookingRequestDTO,
                    @RequestHeader (name = "AccommodationId") id: Long,
                    @RequestHeader (name = "GuestCPF") cpf: String
                    ) : ResponseEntity<Booking> {
        return ResponseEntity.ok(bookingService.save(booking, cpf, id))
    }




    /*





     */

}