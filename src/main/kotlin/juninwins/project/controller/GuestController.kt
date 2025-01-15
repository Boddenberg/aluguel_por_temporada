package juninwins.project.controller

import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
@Validated
class GuestController(val guestService: GuestService) {

    @GetMapping("/search/{cpf}")
    @Operation(summary = "Find a guest")
    fun findGuest(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.findGuestByCPF(cpfCustomer))
    }

    @GetMapping("/searchAll")
    @Operation(summary = "Find all guests")
    fun findAllGuests(): List<Guest> {
        return guestService.findAllGuests()
    }

    @PostMapping("/register/guest")
    @Operation(summary = "Register a guest")
    fun saveGuest(@RequestBody @Valid client: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.saveGuest(client))
    }

    @PostMapping("/register/guest/return")
    @Operation(summary = "Register a guest")
    fun saveGuests(@RequestBody @Valid client: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.saveGuest(client))
    }

    @PutMapping("/update/guest")
    @Operation(summary = "Update a guest")
    fun updateGuest(@RequestBody @Valid client: UpdateGuestDTO): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.updateGuest(client))
    }

    @DeleteMapping("/delete/guest/{cpf}")
    @Operation(summary = "Delete a guest")
    fun deleteGuest(@PathVariable(name = "cpf") cpf: String): ResponseEntity<Void> {
        guestService.deleteGuestByCPF(cpf)
        return ResponseEntity.ok().build()
    }
}
