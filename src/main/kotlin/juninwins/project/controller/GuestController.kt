package juninwins.project.controller

import juninwins.project.model.Guest
import juninwins.project.service.GuestService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.accommodation.GuestAccommodations
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
@Validated
class GuestController (val guestService: GuestService) {


    @GetMapping("/test")
    fun test() = "Hello World"
    @GetMapping("/search/{cpf}")
    fun findGuest(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.findGuestByCPF(cpfCustomer))
    }
    @GetMapping("/search/guest/accommodations/{cpf}")
    fun findGuestAccommodations(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<GuestAccommodations> {
        return ResponseEntity.ok(guestService.findGuestAccommodationsByCPF(cpfCustomer))
    }
    @PostMapping("/register/guest")
    @Operation(summary = "Register a guest")
    fun saveGuest(@RequestBody @Valid cliente: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.save(cliente))
    }

    @PutMapping("/update/{cpf}")
    fun updateGuest(
        @PathVariable(name = "cpf") cpfCustomer: String,
        @RequestBody updatedCustomer: Guest
    ): ResponseEntity<Guest> {
        return try {
            val newUpdatedCustomer = guestService.update(cpfCustomer, updatedCustomer)
            ResponseEntity.ok(newUpdatedCustomer)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/{cpf}")
    fun deleteGuest(
        @PathVariable(name = "cpf") cpfCustomer: String
    ) : ResponseEntity<String> = guestService.deleteById(cpfCustomer)
}
