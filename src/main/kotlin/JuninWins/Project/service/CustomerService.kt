package JuninWins.Project.service

import JuninWins.Project.model.Cliente
import org.springframework.http.ResponseEntity

interface CustomerService {

    fun save(customer : Cliente) : Cliente

    fun findGuestByCPF(cpf : String) : Cliente

    fun update(cpf : String, newCustomer : Cliente) : Cliente

    fun deleteById(cpf : String) : ResponseEntity<String>

}