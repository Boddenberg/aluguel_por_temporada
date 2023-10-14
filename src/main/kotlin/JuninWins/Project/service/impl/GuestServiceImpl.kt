package JuninWins.Project.service.impl

import JuninWins.Project.exceptions.CEPValidationException
import JuninWins.Project.model.Guest
import JuninWins.Project.repository.GuestRepository
import JuninWins.Project.service.GuestService
import JuninWins.Project.utils.validateCEP
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GuestServiceImpl (val guestRepository : GuestRepository) : GuestService {

    private val modelMapper = ModelMapper()
    override fun save(guest: Guest): Guest {
        val cepValidationResult = validateCEP(guest.address.cep)

        if (cepValidationResult != null) {
            throw CEPValidationException(cepValidationResult)
        }

        return guestRepository.save(guest)
    }

    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
    }

    override fun update(cpf: String, newCustomer: Guest): Guest {
        val currentCustomer = findByCPF(cpf)

        modelMapper.map(newCustomer, currentCustomer)

        return guestRepository.save(currentCustomer)
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
}