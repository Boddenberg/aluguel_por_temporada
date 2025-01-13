package juninwins.project.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.DTO.AccommodationDTO
import juninwins.project.service.AccommodationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accommodation")
@Validated
class AccommodationController(val accommodationService: AccommodationService) {

    @PostMapping("/register")
    @Operation(summary = "Register an accommodation")
    fun saveAccommodation(@RequestBody @Valid accommodation: Accommodation): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.saveAccommodation(accommodation))
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Find an accommodation by ID")
    fun findAccommodationById(@PathVariable(name = "id") accommodationId: String): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.findAccommodationById(accommodationId))
    }

    @GetMapping("/searchAll")
    @Operation(summary = "Find all accommodations")
    fun findAllAccommodations(): List<Accommodation> {
        return accommodationService.findAllAccommodations()
    }

    @PutMapping("/update")
    @Operation(summary = "Update an accommodation")
    fun updateAccommodation(@RequestBody @Valid accommodation: AccommodationDTO): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.updateAccommodation(accommodation))
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an accommodation")
    fun deleteAccommodation(@PathVariable(name = "id") accommodationId: String): ResponseEntity<Void> {
        accommodationService.deleteAccommodationById(accommodationId)
        return ResponseEntity.ok().build()
    }
}
