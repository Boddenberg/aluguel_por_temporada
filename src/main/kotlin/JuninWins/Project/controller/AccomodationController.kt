package JuninWins.Project.controller

import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.DiscountPolicy
import JuninWins.Project.service.AccommodationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("accommodations")
class AccommodationController(val accommodationService: AccommodationService) {

    @PostMapping("register/accommodation")
    fun saveAccommodation(@RequestBody accommodation: Accommodation): ResponseEntity<Accommodation> {
        return ResponseEntity.status(201).body(accommodationService.save(accommodation))
    }

    @GetMapping("/search/accommodation/{id}")
    fun findAccommodation(@PathVariable(name = "id") idAccommodation: Long): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.findAccomodationById(idAccommodation))
    }

    @PutMapping("/update/accommodation/{id}")
    fun updateAccommodation(
        @PathVariable(name = "id") idAccommodation: Long,
        @RequestBody updatedAccommodation: Accommodation
    ): ResponseEntity<Accommodation> {
        val newUpdatedAccommodation = accommodationService.update(idAccommodation, updatedAccommodation)
        return ResponseEntity.status(200).body(newUpdatedAccommodation)
    }

    @PatchMapping("/insert/policy/{id}")
    fun insertPolicyRuleOnAccommodation(
        @PathVariable(name = "id") id: Long,
        @RequestBody discountPolicy: DiscountPolicy
    ): ResponseEntity<Accommodation> {
        return ResponseEntity.ok(accommodationService.insertPolicyOnAccommodation(id, discountPolicy))
    }

    @PutMapping("/update/policy")
    fun updatedPolicyRuleOnAccommodation(
        @RequestHeader(name = "idAccommodation") idAccommodation: Long,
        @RequestHeader(name = "idPolicy") idPolicy: Long,
        @RequestBody discountPolicy: DiscountPolicy
    ): ResponseEntity<List<DiscountPolicy>> {
        return ResponseEntity.ok(
            accommodationService.updatedPolicyOnAccommodation(
                idAccommodation,
                idPolicy,
                discountPolicy
            )
        )
    }

    @DeleteMapping("/delete/policy")
    fun deletePolicyRuleOnAccommodation(
        @RequestHeader(name = "idPolicy") idPolicy: Long
    ) {
        accommodationService.deletePolicyById(idPolicy)
    }

    @DeleteMapping("/delete/accommodation/{id}")
    fun deleteAccommodation(
        @PathVariable(name = "id") idAccommodation: Long
    ): ResponseEntity<String> {
        return accommodationService.deleteById(idAccommodation)
    }
}