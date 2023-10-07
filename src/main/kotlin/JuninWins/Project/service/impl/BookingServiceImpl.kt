package JuninWins.Project.service.impl

import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Booking
import JuninWins.Project.repository.BookingRepository
import JuninWins.Project.service.BookingService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class BookingServiceImpl (val bookingRepository: BookingRepository) : BookingService {

    private val modelMapper = ModelMapper()

    override fun save(booking: Booking): Booking {
        return bookingRepository.save(booking)
    }

    override fun findBookingById(id: String): Booking {
        return findById(id)
    }

    override fun update(id: String, newBooking: Booking): Booking {
        val currentBooking = findById(id)

        modelMapper.map(newBooking, currentBooking)

        return bookingRepository.save(currentBooking)

    }

    override fun deleteById(id: String): ResponseEntity<String> {
            val booking = bookingRepository.findById(id)

        if (booking.isPresent) {
            bookingRepository.deleteById(id)
            return ResponseEntity.ok("Booking deleted with success!")
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking ID not found")
    }


    private fun findById(id: String): Booking {
        return bookingRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }

}