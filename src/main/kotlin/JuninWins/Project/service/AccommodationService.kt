package JuninWins.Project.service

import JuninWins.Project.model.Accommodation
import org.springframework.http.ResponseEntity

interface AccommodationService {

    fun save(accomocation : Accommodation) : Accommodation

    fun findAccomodationById(id : String) : Accommodation

    fun update(id : String, newAccomodation: Accommodation) : Accommodation

    fun deleteById(id : String) : ResponseEntity<String>

}