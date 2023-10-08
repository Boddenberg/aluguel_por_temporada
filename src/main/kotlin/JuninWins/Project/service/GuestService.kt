package JuninWins.Project.service

import JuninWins.Project.model.Guest
import org.springframework.http.ResponseEntity

interface GuestService {

    fun save(customer : Guest) : Guest

    fun findGuestByCPF(cpf : String) : Guest

    fun update(cpf : String, newCustomer : Guest) : Guest

    fun deleteById(cpf : String) : ResponseEntity<String>

}