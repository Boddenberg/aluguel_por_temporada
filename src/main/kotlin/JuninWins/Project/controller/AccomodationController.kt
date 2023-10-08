package JuninWins.Project.controller

import JuninWins.Project.model.Accommodation
import JuninWins.Project.service.AccommodationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accommodations")
class AccomodationController (val accommodationService: AccommodationService) {

    @PostMapping("register/accommodation")
    fun saveAccommodation(@RequestBody accommodation: Accommodation) : ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.save(accommodation))
    }

    @GetMapping("/search/accommodation/{id}")
    fun findAccommodation(@PathVariable(name = "id") idAccommodation: Long) : ResponseEntity<Accommodation> {
        return  ResponseEntity.ok(accommodationService.findAccomodationById(idAccommodation))
    }//TODO: Quando busca por ID não existente está voltando um stacktrace gigantesco

    @PutMapping("/update/accommodation/{id}")
    fun updateAccommodation(@PathVariable(name = "id") idAccommodation: Long,
    @RequestBody updatedAccommodation: Accommodation) : ResponseEntity<Accommodation>{
        return try {
            val newUpdatedAccommodation = accommodationService.update(idAccommodation, updatedAccommodation)
            ResponseEntity.ok(newUpdatedAccommodation)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }//TODO: Quando busca por ID não existente está voltando um stacktrace gigantesco

    @DeleteMapping("/delete/accommodation/{id}")
    fun deleteAccommodation(
        @PathVariable(name = "id") idAccommodation: Long) : ResponseEntity<String> {

        return accommodationService.deleteById(idAccommodation)

    }//TODO: Quando tenta excluir acomodação com ID 1 devolve stacktrace gigante


}