package juninwins.project.service.impl

import BookingUtils
import juninwins.project.DTO.BookingRequestDTO
import juninwins.project.enums.StatusReservaEnum
import juninwins.project.exceptions.AccommodationDateRangeException
import juninwins.project.exceptions.AccommodationIdNotFoundException
import juninwins.project.exceptions.StartDatateIsEqualOrAfterEndDateException
import juninwins.project.model.Booking
import juninwins.project.repository.BookingRepository
import juninwins.project.service.AccommodationService
import juninwins.project.service.BookingService
import juninwins.project.service.GuestService
import juninwins.project.service.NotificationService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookingServiceImpl(
    val bookingRepository: BookingRepository,
    val guestService: GuestService,
    val accommodationService: AccommodationService,
    val notificationService: NotificationService
) : BookingService {

    private val modelMapper = ModelMapper()

    override fun save(booking: BookingRequestDTO, cpf: String, id: Long): Booking {

        val guest = guestService.findGuestByCPF(cpf)
        val accommodation = accommodationService.findAccomodationById(id)

        val startDate = booking.startDate
        val endDate = booking.endDate

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw StartDatateIsEqualOrAfterEndDateException(startDate.dayOfMonth.toString())
        }

        val findByAccommodationAndDateRange =
            bookingRepository.findByAccommodationAndDateRange(accommodation, startDate, endDate)

        if (findByAccommodationAndDateRange != null) {
            throw AccommodationDateRangeException(startDate.dayOfMonth.toString(), endDate.dayOfMonth.toString())
        }

        val bookingDuration = BookingUtils.calculateBookingDurationDays(startDate, endDate)
        val totalPrice = BookingUtils.calculateBookingTotalPrice(accommodation.basePrice, bookingDuration, accommodation)

        val newBooking = Booking(
            accommodation,
            startDate,
            endDate,
            bookingDuration,
            totalPrice,
            guest,
            StatusReservaEnum.CONFIRMED
        )

        val reservation = bookingRepository.save(newBooking)
        notificationService.sendSmsMessage(guest.phoneNumber, "Successfully registered booking")
        return reservation
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
