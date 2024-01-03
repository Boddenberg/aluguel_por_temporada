package juninwins.project.service.impl

import juninwins.project.enums.StatusReservaEnum
import juninwins.project.exceptions.*
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.booking.Booking
import juninwins.project.model.guest.Guest
import juninwins.project.model.review.ReviewByGuest
import juninwins.project.repository.AccommodationRepository
import juninwins.project.repository.BookingRepository
import juninwins.project.repository.HostAccommodationsRepository
import juninwins.project.repository.GuestRepository
import juninwins.project.service.GuestService
import juninwins.project.utils.validateCEP
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@Service
class GuestServiceImpl (val guestRepository : GuestRepository,
        val accommodationRepository: AccommodationRepository,
        val hostAccommodationsRepository: HostAccommodationsRepository,
        val bookingRepository: BookingRepository) : GuestService {

    private val modelMapper = ModelMapper()

    override fun save(customer: Guest): Guest {
        validateCepForAddress(customer)
        checkIfGuestExists(customer)
        checkAndSetGuestResponsibility(customer)
        return guestRepository.save(customer)
    }
    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
    }

    override fun reviewAccommodation(hostCPF: String, guestCPF : String, idBooking: Long, idAccommodation: Long, review: ReviewByGuest): Accommodation {
        val currentHost = findByCPF(hostCPF)
        val currentGuest = findByCPF(guestCPF)
        val currentHostAccommodation = hostAccommodationsRepository.findByGuest(currentHost)
        val currentBooking = bookingRepository.findById(idBooking).get()
        val currentAccommodation = currentHostAccommodation.get()

        validateBookingForReview(currentBooking)

            review.madeByCPF = currentGuest.cpf
            review.madeByName = currentGuest.name
            currentBooking.reviwedByGuest = true

        val accommodationToUpdate = currentAccommodation.accommodations.find { it.id == idAccommodation }
        accommodationToUpdate?.let { accommodation ->
            accommodation.reviews = (accommodation.reviews ?: mutableListOf()).apply {
                add(review)
            }
            hostAccommodationsRepository.save(currentAccommodation)
            return accommodation
        }
        throw Exception("Acomodação não encontrada")
    }

    private fun validateBookingForReview(booking: Booking) {
        if (booking.reviwedByGuest == true) {
            throw BookingAlreadyReviewedException()
        }
        if (booking.status != StatusReservaEnum.CONCLUDED || booking.reviewStatus != StatusReservaEnum.READY_TO_REVIEW) {
            throw BookingNotConcludedException(booking.id)
        }
    }

    override fun update(cpf: String, newCustomer: Guest): Guest {
        validateCepForAddress(newCustomer)
        val currentGuest = findByCPF(cpf)

        if(currentGuest.cpf != newCustomer.cpf) {
            throw CPFNotAuthorizeToUpdateException(currentGuest.cpf)
        }

        modelMapper.map(newCustomer, currentGuest)

        return guestRepository.save(currentGuest)
    }

    override fun deleteById(cpf: String): ResponseEntity<String> {
        val guest = guestRepository.findById(cpf)

        if (guest.isPresent) {
            guestRepository.deleteById(cpf)
            return ResponseEntity.ok("Guest deleted with success!")
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest CPF not found!!")
    }

    private fun findByCPF(cpf: String): Guest {
        return guestRepository.findById(cpf).orElseThrow { Exception("Guest CPF not found!")}

    }

    private fun checkIfGuestExists(customer: Guest) {
        val currentGuest = guestRepository.findById(customer.cpf)
        if (currentGuest.isPresent) {
            throw GuestAlreadyRegisteredException(customer.cpf)
        }
    }

    private fun checkAndSetGuestResponsibility(customer: Guest) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val birthdate = LocalDate.parse(customer.birthDate, formatter)
        val today = LocalDate.now()
        val age = Period.between(birthdate, today).years
        if (age >= 18) {
            customer.responsible = true
        }
    }


    private fun validateCepForAddress(customer: Guest) {
        val cepValidationResult = validateCEP(customer.address.cep)

        if (cepValidationResult != null) {
            throw CEPValidationException(cepValidationResult)
        }
    }
}