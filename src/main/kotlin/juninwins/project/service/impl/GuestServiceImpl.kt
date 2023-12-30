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

@Service
class GuestServiceImpl (val guestRepository : GuestRepository) : GuestService {

    private val modelMapper = ModelMapper()
    override fun save(guest: Guest): Guest {
        validateCepForAddress(guest)
        return saveGuest(guest)
    }

    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
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
        var currentGuest = guestRepository.findById(guest.cpf)
        if (currentGuest.isPresent) {
            throw GuestAlreadyRegisteredException(guest.cpf)
        }
        return guestRepository.save(guest)
    }
    private fun findByCPF(cpf: String): Guest {
        return guestRepository.findById(cpf).orElseThrow { Exception("Guest CPF not found!")}

    }

    private fun validateCepForAddress(guest: Guest) {
        val cepValidationResult = validateCEP(guest.address.cep)

        if (cepValidationResult != null) {
            throw CEPValidationException(cepValidationResult)
        }
    }
}