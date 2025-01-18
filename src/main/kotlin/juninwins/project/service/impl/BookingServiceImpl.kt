package juninwins.project.service.impl

import juninwins.project.model.booking.Booking
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingServiceImpl(val bookingService: BookingService) {

    fun findBookingById(id: String): Booking {
        return bookingRepository.findById(id).orElseThrow {
            IllegalArgumentException("Booking with ID $id not found")
        }
    }
}
