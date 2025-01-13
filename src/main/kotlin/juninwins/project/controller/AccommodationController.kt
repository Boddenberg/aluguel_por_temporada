package juninwins.project.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.service.AccommodationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accommodation")
@Validated
class AccommodationController(val accommodationService: AccommodationService) {

   /* @GetMapping("/search/{cpf}")
    @Operation(summary = "Find a guest")
    fun findGuest(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<Guest> {
        return ResponseEntity.ok(accommodationService.findGuestByCPF(cpfCustomer))
    }

    @GetMapping("/searchAll")
    @Operation(summary = "Find all guests")
    fun findAllGuests(): List<Guest> {
        return accommodationService.findAllGuests()
    }*/

    @PostMapping("/register/accommodation")
    @Operation(summary = "Register a accommodation")
    fun saveGuest(@RequestBody @Valid accommodation: Accommodation): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.saveAccommodation(accommodation))
    }

  /*  @PostMapping("/register/guest/return")
    @Operation(summary = "Register a guest")
    fun saveGuests(@RequestBody @Valid client: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(accommodationService.save(client))
    }

    @PutMapping("/update/guest")
    @Operation(summary = "Update a guest")
    fun updateGuest(@RequestBody @Valid client: UpdateGuestDTO): ResponseEntity<Guest> {
        return ResponseEntity.ok(accommodationService.updateGuest(client))
    }

    @DeleteMapping("/delete/guest/{cpf}")
    @Operation(summary = "Delete a guest")
    fun deleteGuest(@PathVariable(name = "cpf") cpf: String): ResponseEntity<Void> {
        accommodationService.deleteGuestByCPF(cpf)
        return ResponseEntity.ok().build()
    }*/
}
