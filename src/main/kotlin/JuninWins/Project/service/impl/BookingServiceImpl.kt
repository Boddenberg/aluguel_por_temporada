package JuninWins.Project.service.impl

import BookingUtils
import JuninWins.Project.DTO.BookingRequestDTO
import JuninWins.Project.enums.StatusReservaEnum
import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Booking
import JuninWins.Project.repository.BookingRepository
import JuninWins.Project.service.AccommodationService
import JuninWins.Project.service.BookingService
import JuninWins.Project.service.GuestService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookingServiceImpl(
    val bookingRepository: BookingRepository,
    val guestService: GuestService,
    val accommodationService: AccommodationService
) : BookingService {

    private val modelMapper = ModelMapper()

    override fun save(booking: BookingRequestDTO, cpf: String, id: Long): Booking {
        val guest = guestService.findGuestByCPF(cpf)
        val accommodation = accommodationService.findAccomodationById(id)
        val bookingDuration = BookingUtils.calculateBookingDurationDays(booking.startDate, booking.endDate)
        val totalPrice = BookingUtils.calculateBookingTotalPrice(accommodation.basePrice, bookingDuration, accommodation)

        val newBooking = Booking(
            accommodation,
            booking.startDate,
            booking.endDate,
            bookingDuration,
            totalPrice,
            guest,
            StatusReservaEnum.CANCELED
        )

        return bookingRepository.save(newBooking)
    }

    override fun findBookingById(id: Long): Booking {
        return findById(id)
    }

    override fun update(id: Long, newBooking: Booking): Booking {
        val currentBooking = findById(id)

        modelMapper.map(newBooking, currentBooking)

        return bookingRepository.save(currentBooking)
    }

    override fun deleteById(id: Long): ResponseEntity<String> {
        val booking = bookingRepository.findById(id)

        if (booking.isPresent) {
            bookingRepository.deleteById(id)
            return ResponseEntity.ok("Booking deleted with success!")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking ID not found")
    }

    private fun findById(id: Long): Booking {
        return bookingRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }


}
