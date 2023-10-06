package JuninWins.Project.service.impl

import JuninWins.Project.model.Cliente
import JuninWins.Project.repository.CustomerRepository
import JuninWins.Project.service.CustomerService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerServiceImplement (val customerRepository : CustomerRepository) : CustomerService {

    private val modelMapper = ModelMapper()
    override fun save(customer: Cliente) : Cliente {
       return customerRepository.save(customer)
    }

    override fun findGuestByCPF(cpf: String) : Cliente {
        return findByCPF(cpf)
    }

    override fun update(cpf: String, newCustomer: Cliente): Cliente {
        val currentCustomer = findByCPF(cpf)

        modelMapper.map(newCustomer, currentCustomer)

        return customerRepository.save(currentCustomer)
    }

    override fun deleteById(cpf: String): ResponseEntity<String> {
        val guest = customerRepository.findById(cpf)

        if (guest.isPresent) {
            customerRepository.deleteById(cpf)
            return ResponseEntity.ok("Hóspede excluído com sucesso!")
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado")
    }

    private fun findByCPF(cpf: String): Cliente {
        return customerRepository.findById(cpf).orElseThrow { Exception("CPF não encontrado!")}

    }

    private fun updatedCustomer(currentCustomer: Cliente?, novoCliente: Cliente) {
        currentCustomer?.apply {
            nome = novoCliente.nome
            sobrenome = novoCliente.sobrenome
            email = novoCliente.email
            telefone = novoCliente.telefone
            dataNascimento = novoCliente.dataNascimento
            cpf = novoCliente.cpf
            responsavel = novoCliente.responsavel
            endereco = novoCliente.endereco
        }
    }

}