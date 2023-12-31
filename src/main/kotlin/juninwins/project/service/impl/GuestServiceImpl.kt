package juninwins.project.service.impl

import juninwins.project.exceptions.AccommodationIdNotFoundException
import juninwins.project.exceptions.CEPValidationException
import juninwins.project.exceptions.CPFNotAuthorizeToUpdateException
import juninwins.project.exceptions.GuestAlreadyRegisteredException
import juninwins.project.model.Guest
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.GuestAccommodations
import juninwins.project.repository.AccommodationRepository
import juninwins.project.repository.GuestAccommodationsRepository
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
        val guestAccommodationsRepository: GuestAccommodationsRepository,
                       val accommodationRepository: AccommodationRepository) : GuestService {

    private val modelMapper = ModelMapper()
    override fun save(guest: Guest): Guest {
        validateCepForAddress(guest)
        return saveGuest(guest)
    }

    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
    }
    override fun findGuestAccommodationsByCPF(cpf: String) : GuestAccommodations {
        return findGuestAccommodations(cpf)
    }

    override fun update(cpf: String, newGuest: Guest): Guest {
        validateCepForAddress(newGuest)
        val currentGuest = findByCPF(cpf)

        if(currentGuest.cpf != newGuest.cpf) {
            throw CPFNotAuthorizeToUpdateException(currentGuest.cpf)
        }

        modelMapper.map(newGuest, currentGuest)

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

    private fun saveGuest(guest: Guest): Guest {

        val currentGuest = guestRepository.findById(guest.cpf)
        if (currentGuest.isPresent) {
            throw GuestAlreadyRegisteredException(guest.cpf)
        }

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val birthdate = LocalDate.parse(guest.birthDate, formatter)
        val today = LocalDate.now()

        val age = Period.between(birthdate, today).years

        if (age >= 18) {
            guest.responsible = true
        }

        return guestRepository.save(guest)
    }


    private fun findByCPF(cpf: String): Guest {
        return guestRepository.findById(cpf).orElseThrow { Exception("Guest CPF not found!")}

    }

    private fun findAccommodationById(id: Long): Accommodation {
        return accommodationRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }
    private fun findGuestAccommodations(cpf: String): GuestAccommodations {
        return guestAccommodationsRepository.findById(cpf).orElseThrow { Exception("Guest CPF not found!")}
    }

    private fun validateCepForAddress(guest: Guest) {
        val cepValidationResult = validateCEP(guest.address.cep)

        if (cepValidationResult != null) {
            throw CEPValidationException(cepValidationResult)
        }
    }
}