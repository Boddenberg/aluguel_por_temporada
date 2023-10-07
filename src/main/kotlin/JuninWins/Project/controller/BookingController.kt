package JuninWins.Project.controller

import JuninWins.Project.repository.BookingRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bookings")
class BookingController(val bookingRepository: BookingRepository) {

    @GetMapping
    fun getAllBookings() = bookingRepository.findAll()



}