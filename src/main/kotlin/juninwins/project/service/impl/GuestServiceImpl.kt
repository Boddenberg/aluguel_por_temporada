package juninwins.project.service.impl

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.review.Review
import juninwins.project.service.GuestService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.Key
import java.util.*

@Service
class GuestServiceImpl (
//    val guestRepository : GuestRepository,
        val dynamoDbTemplate: DynamoDbTemplate,
//        val accommodationRepository: AccommodationRepository,
//        val hostAccommodationsRepository: HostAccommodationsRepository,
//        val bookingRepository: BookingRepository
) : GuestService {

    private val modelMapper = ModelMapper()

//        validateCepForAddress(customer)
//        checkIfGuestAlreadyExists(customer)
//        checkAndSetGuestResponsibility(customer)

    override fun save(customer: GuestComplete): GuestComplete {

        return dynamoDbTemplate.save(customer)
    }

    override fun saveReview(review: Review): Review {
        return dynamoDbTemplate.save(review)
    }


    override fun findGuestByCPF(cpf: String) : GuestComplete {
        return findByCPF(cpf)
    }
//
//    override fun reviewAccommodationByGuest(hostCPF: String, guestCPF : String, idBooking: Long, idAccommodation: Long, review: ReviewByGuest): Accommodation {
//        val currentHost = findByCPF(hostCPF)
//        val currentGuest = findByCPF(guestCPF)
//        val currentBooking = findBookingById(idBooking)
//
//        val currentHostAccommodation = hostAccommodationsRepository.findByGuest(currentHost)
//        val currentAccommodation = currentHostAccommodation.get()
//
//        validateBookingForGuestReview(currentBooking)
//
//            review.madeByCPF = currentGuest.cpf
//            review.madeByName = currentGuest.name
//            currentBooking.reviwedByGuest = true
//
//        val accommodationToUpdate = currentAccommodation.accommodations.find { it.id == idAccommodation }
//        accommodationToUpdate?.let { accommodation ->
//            accommodation.reviews = (accommodation.reviews ?: mutableListOf()).apply {
//                add(review)
//            }
//            hostAccommodationsRepository.save(currentAccommodation)
//            return accommodation
//        }
//        throw Exception("Acomodação não encontrada")
//    }
//
//    override fun reviewGuestByHost(hostCPF: String, guestCPF: String, idBooking: Long, review: ReviewByHost): Guest {
//        val currentHost = findByCPF(hostCPF)
//        val currentGuest = findByCPF(guestCPF)
//        val currentBooking = findBookingById(idBooking)
//        val currentHostAccommodation = findHostAccommodationsById(currentHost)
//
//        validateBookingForHostReview(currentBooking)
//
//        return if (currentGuest.cpf != currentHostAccommodation.guest.cpf) {
//            review.madeByCPF = currentHost.cpf
//            review.madeByName = currentHost.name
//            currentBooking.reviwedByHost = true
//            val existingReviews = currentGuest.reviews ?: mutableListOf()
//            existingReviews.add(review)
//            currentGuest.reviews = existingReviews
//            guestRepository.save(currentGuest)
//        } else {
//            throw Exception("Guest should review an accommodation")
//        }
//    }
//
//    private fun validateBookingForHostReview(booking: Booking) {
//        if (booking.reviwedByHost == true) {
//            throw BookingAlreadyReviewedException()
//        }
//        if (booking.status != StatusReservaEnum.CONCLUDED || booking.reviewStatus != StatusReservaEnum.READY_TO_REVIEW) {
//            throw BookingNotConcludedException(1)
//        }//o 1 era o id
//    }
//    private fun validateBookingForGuestReview(booking: Booking) {
//        if (booking.reviwedByGuest == true) {
//            throw BookingAlreadyReviewedException()
//        }
//        if (booking.status != StatusReservaEnum.CONCLUDED || booking.reviewStatus != StatusReservaEnum.READY_TO_REVIEW) {
//            throw BookingNotConcludedException(1)
//        }
//    }
//
//    override fun update(cpf: String, newCustomer: Guest): Guest {
//        validateCepForAddress(newCustomer)
//        val currentGuest = findByCPF(cpf)
//
//        if(currentGuest.cpf != newCustomer.cpf) {
//            throw CPFNotAuthorizeToUpdateException(currentGuest.cpf)
//        }
//
//        modelMapper.map(newCustomer, currentGuest)
//
//        return guestRepository.save(currentGuest)
//    }
//
//    override fun deleteById(cpf: String): ResponseEntity<String> {
//        val guest = guestRepository.findById(cpf)
//
//        if (guest.isPresent) {
//            guestRepository.deleteById(cpf)
//            return ResponseEntity.ok("Guest deleted with success!")
//        }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest CPF not found!!")
//    }

    private fun findByCPF(cpf: String): GuestComplete {
        return Optional.ofNullable(dynamoDbTemplate.load(Key.builder().partitionValue(cpf).build(), GuestComplete::class.java)).orElseThrow { Exception("Guest CPF not found!")}
    }

//    private fun checkIfGuestAlreadyExists(customer: Guest) {
//        val currentGuest = guestRepository.findById(customer.cpf)
//        if (currentGuest.isPresent) {
//            throw GuestAlreadyRegisteredException(customer.cpf)
//        }
//    }
//
//    private fun checkAndSetGuestResponsibility(customer: Guest) {
//        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//        val birthdate = LocalDate.parse(customer.birthDate, formatter)
//        val today = LocalDate.now()
//        val age = Period.between(birthdate, today).years
//        if (age >= 18) {
//            customer.responsible = true
//        }
//    }
//    private fun validateCepForAddress(customer: Guest) {
//        val cepValidationResult = validateCEP(customer.address.cep)
//
//        if (cepValidationResult != null) {
//            throw CEPValidationException(cepValidationResult)
//        }
//    }
//
//    private fun findBookingById(idBooking: Long): Booking {
//        return try {
//            bookingRepository.findById(idBooking).get()
//        } catch (e: NoSuchElementException) {
//            throw BookingNotFoundException(idBooking.toString())
//        }
//    }
//
//    private fun findHostAccommodationsById(currentClient: Guest): HostAccommodations {
//        return try {
//            hostAccommodationsRepository.findByGuest(currentClient).get()
//        } catch (e: NoSuchElementException) {
//            throw Exception("HostAccommodations Not Found")
//        }
//    }

}