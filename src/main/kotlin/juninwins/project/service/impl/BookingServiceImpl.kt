package juninwins.project.service.impl

import juninwins.project.model.booking.Booking
import juninwins.project.repository.BookingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingService(val bookingRepository: BookingRepository) {

    fun findBookingById(id: String): Booking {
        return bookingRepository.findById(id).orElseThrow {
            IllegalArgumentException("Booking with ID $id not found")
        }
    }