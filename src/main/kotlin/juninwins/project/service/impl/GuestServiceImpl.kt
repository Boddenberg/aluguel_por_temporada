package juninwins.project.service.impl

import juninwins.project.exceptions.CEPValidationException
import juninwins.project.exceptions.CPFNotAuthorizeToUpdateException
import juninwins.project.exceptions.GuestAlreadyRegisteredException
import juninwins.project.model.Guest
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
class GuestServiceImpl (val guestRepository : GuestRepository) : GuestService {

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