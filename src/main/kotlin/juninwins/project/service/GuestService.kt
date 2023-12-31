package juninwins.project.service

import juninwins.project.model.Guest
import juninwins.project.model.accommodation.GuestAccommodations
import org.springframework.http.ResponseEntity

interface GuestService {

    fun save(customer : Guest) : Guest
    fun findGuestByCPF(cpf : String) : Guest
    fun findGuestAccommodationsByCPF(cpf : String) : GuestAccommodations
    fun update(cpf : String, newCustomer : Guest) : Guest
    fun deleteById(cpf : String) : ResponseEntity<String>

}