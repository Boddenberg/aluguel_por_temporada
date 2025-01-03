package juninwins.project.controller

import juninwins.project.model.guest.GuestComplete
import juninwins.project.service.GuestService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.review.Review
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
@Validated
class GuestController (val guestService: GuestService) {

    @GetMapping("/search/{cpf}")
    fun findGuest(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<GuestComplete> {

        return ResponseEntity.ok(guestService.findGuestByCPF(cpfCustomer))
    }

    @GetMapping("/searchAll")
    fun findGuest(): List<GuestComplete> {

        return guestService.findAllGuests()
    }

    @PostMapping("/register/guest")
    @Operation(summary = "Register a guest")
    fun saveGuest(@RequestBody @Valid cliente: GuestComplete): ResponseEntity<GuestComplete> {
        val debug = cliente
        return ResponseEntity.ok(guestService.save(cliente))
    }

    @PostMapping("/register/review")
    @Operation(summary = "Register a review")
    fun saveReview(@RequestBody @Valid review: Review): ResponseEntity<Review> {
        return ResponseEntity.ok(guestService.saveReview(review))
    }

}
