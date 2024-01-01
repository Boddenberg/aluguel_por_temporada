package juninwins.project.service.impl

import BookingUtils
import juninwins.project.DTO.BookingRequestDTO
import juninwins.project.enums.StatusReservaEnum
import juninwins.project.exceptions.AccommodationDateRangeException
import juninwins.project.exceptions.AccommodationIdNotFoundException
import juninwins.project.exceptions.SameGuestAndHostException
import juninwins.project.exceptions.StartDatateIsEqualOrAfterEndDateException
import juninwins.project.model.booking.Booking
import juninwins.project.repository.BookingRepository
import juninwins.project.repository.GuestAccommodationsRepository
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
    val notificationService: NotificationService,
        val guestAccommodationsRepository: GuestAccommodationsRepository
) : BookingService {

    private val modelMapper = ModelMapper()

    override fun save(booking: BookingRequestDTO, hostCPF: String, guestCPF: String, idAccommodation: Long): Booking {

        if (hostCPF == guestCPF) {
            throw SameGuestAndHostException()
        }

        val guest = guestService.findGuestByCPF(guestCPF)
        val host = guestService.findGuestByCPF(hostCPF)

        val currentHost = guestAccommodationsRepository.findByGuest(host).get()
        val currentAccommodation = currentHost.accommodations.find { it.id == idAccommodation }
                ?: throw AccommodationIdNotFoundException(idAccommodation)

        val startDate = booking.startDate
        val endDate = booking.endDate

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw StartDatateIsEqualOrAfterEndDateException(startDate.dayOfMonth.toString())
        }

        val findByAccommodationAndDateRange =
                bookingRepository.findByAccommodationAndDateRange(currentAccommodation, startDate, endDate)

        if (findByAccommodationAndDateRange != null) {
            throw AccommodationDateRangeException(startDate.dayOfMonth.toString(), endDate.dayOfMonth.toString())
        }

        val bookingDuration = BookingUtils.calculateBookingDurationDays(startDate, endDate)
        val totalPrice = BookingUtils.calculateBookingTotalPrice(currentAccommodation.basePrice, bookingDuration, currentAccommodation)

        val newBooking = Booking(
                accommodation = currentAccommodation,
                startDate = startDate,
                endDate = endDate,
                bookingDuration = bookingDuration,
                totalPrice = totalPrice,
                guest = guest,
                host = host,
                status = StatusReservaEnum.CONFIRMED
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
