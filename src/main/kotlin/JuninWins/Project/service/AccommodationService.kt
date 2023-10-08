package JuninWins.Project.service

import JuninWins.Project.model.Accommodation
import org.springframework.http.ResponseEntity

interface AccommodationService {

    fun save(accomocation : Accommodation) : Accommodation

    fun findAccomodationById(id : Long) : Accommodation

    fun update(id : Long, newAccomodation: Accommodation) : Accommodation

    fun deleteById(id : Long) : ResponseEntity<String>

}