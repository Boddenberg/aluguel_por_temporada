package JuninWins.Project.service.impl

import JuninWins.Project.model.Guest
import JuninWins.Project.repository.GuestRepository
import JuninWins.Project.service.GuestService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GuestServiceImpl (val customerRepository : GuestRepository) : GuestService {

    private val modelMapper = ModelMapper()
    override fun save(customer: Guest) : Guest {
       return customerRepository.save(customer)
    }

    override fun findGuestByCPF(cpf: String) : Guest {
        return findByCPF(cpf)
    }

    override fun update(cpf: String, newCustomer: Guest): Guest {
        val currentCustomer = findByCPF(cpf)

        modelMapper.map(newCustomer, currentCustomer)

        return customerRepository.save(currentCustomer)
    }

    override fun deleteById(cpf: String): ResponseEntity<String> {
        val guest = customerRepository.findById(cpf)

        if (guest.isPresent) {
            customerRepository.deleteById(cpf)
            return ResponseEntity.ok("Guest deleted with success!")
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest CPF not found!!")
    }

    private fun findByCPF(cpf: String): Guest {
        return customerRepository.findById(cpf).orElseThrow { Exception("Guest CPF not found!")}

    }

    private fun updatedCustomer(currentCustomer: Guest?, novoCliente: Guest) {
        currentCustomer?.apply {
            name = novoCliente.name
            lastName = novoCliente.lastName
            email = novoCliente.email
            phoneNumber = novoCliente.phoneNumber
            birthDate = novoCliente.birthDate
            cpf = novoCliente.cpf
            responsible = novoCliente.responsible
            address = novoCliente.address
        }
    }

}