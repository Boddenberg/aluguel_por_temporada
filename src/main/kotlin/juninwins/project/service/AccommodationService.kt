package juninwins.project.service

import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.DiscountPolicy
import juninwins.project.model.accommodation.GuestAccommodations
import org.springframework.http.ResponseEntity

interface AccommodationService {

    fun save(accommodation: Accommodation, cpf : String): GuestAccommodations
    fun findAccommodationById(id : Long) : Accommodation

    fun update(id : Long, newAccomodation: Accommodation) : Accommodation

    fun deleteById(id : Long) : ResponseEntity<String>

    fun insertPolicyOnAccommodation(id: Long, discountPolicy: DiscountPolicy): Accommodation

    fun updatedPolicyOnAccommodation(idAccommodation: Long, idPolicy: Long, discountPolicy: DiscountPolicy): List<DiscountPolicy>

    fun deletePolicyById(id : Long) : ResponseEntity<String>
}