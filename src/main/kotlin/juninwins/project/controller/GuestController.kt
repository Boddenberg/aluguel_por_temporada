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

//    @PostMapping("/save/review/accommodation/{hostCPF}/{guestCPF}/{idBooking}/{idAccommodation}")
//    fun reviewAccommodation(@PathVariable(name = "hostCPF") hostCPF: String,
//            @PathVariable(name = "guestCPF") guestCPF : String,
//            @PathVariable(name = "idBooking") idBooking: Long,
//            @PathVariable(name = "idAccommodation") idAccommodation: Long,
//            @RequestBody review: ReviewByGuest) : ResponseEntity<Accommodation> {
//        return ResponseEntity.ok(guestService.reviewAccommodationByGuest(hostCPF, guestCPF, idBooking, idAccommodation, review))
//    }
//
//    @PostMapping("/save/review/guest/{hostCPF}/{guestCPF}/{idBooking}")
//    fun reviewGuest(@PathVariable(name = "hostCPF") hostCPF: String,
//            @PathVariable(name = "guestCPF") guestCPF : String,
//            @PathVariable(name = "idBooking") idBooking: Long,
//            @RequestBody review: ReviewByHost) : ResponseEntity<Guest> {
//        return ResponseEntity.ok(guestService.reviewGuestByHost(hostCPF, guestCPF, idBooking, review))
//    }

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

//    @PutMapping("/update/{cpf}")
//    fun updateGuest(
//        @PathVariable(name = "cpf") cpfCustomer: String,
//        @RequestBody updatedCustomer: Guest
//    ): ResponseEntity<Guest> {
//        return try {
//            val newUpdatedCustomer = guestService.update(cpfCustomer, updatedCustomer)
//            ResponseEntity.ok(newUpdatedCustomer)
//        } catch (e: NoSuchElementException) {
//            ResponseEntity.notFound().build()
//        }
//    }
//
//    @DeleteMapping("/delete/{cpf}")
//    fun deleteGuest(
//        @PathVariable(name = "cpf") cpfCustomer: String
//    )
//            : ResponseEntity<String> = guestService.deleteById(cpfCustomer)
}
