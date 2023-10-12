package JuninWins.Project.service

import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.DiscountPolicy
import org.springframework.http.ResponseEntity

interface AccommodationService {

    fun save(accomocation : Accommodation) : Accommodation

    fun findAccomodationById(id : Long) : Accommodation

    fun update(id : Long, newAccomodation: Accommodation) : Accommodation

    fun deleteById(id : Long) : ResponseEntity<String>

    fun insertPolicyOnAccommodation(id: Long, discountPolicy: DiscountPolicy): Accommodation

    fun updatedPolicyOnAccommodation(idAccommodation: Long, idPolicy: Long, discountPolicy: DiscountPolicy): List<DiscountPolicy>

    fun deletePolicyById(id : Long) : ResponseEntity<String>
}