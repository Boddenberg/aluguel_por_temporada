package JuninWins.Project.service.impl

import JuninWins.Project.model.Cliente
import JuninWins.Project.repository.CustomerRepository
import JuninWins.Project.service.CustomerService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class CustomerServiceImplement (val customerRepository : CustomerRepository) : CustomerService {

    private val modelMapper = ModelMapper()
    override fun save(customer: Cliente) : Cliente {
       return customerRepository.save(customer)
    }

    override fun findById(cpf: String) : Cliente {
        return customerRepository.findById(cpf).get()
    }

    override fun update(cpf: String, newCustomer: Cliente): Cliente {
        val currentCustomer = customerRepository.findById(cpf)
            .orElseThrow { NoSuchElementException("Customer not found with ID $cpf") }

        modelMapper.map(newCustomer, currentCustomer)

        return customerRepository.save(currentCustomer)
    }
    override fun deleteById(cpf: String) {
        customerRepository.deleteById(cpf)
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