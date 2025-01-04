package juninwins.project.controller

import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import juninwins.project.model.review.Review
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

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
    fun saveGuest(@RequestBody @Valid cliente: Guest): ResponseEntity<Void> {
        guestService.save(cliente)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/register/guest/return")
    @Operation(summary = "Register a guest")
    fun saveGuests(@RequestBody @Valid cliente: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.save(cliente))
    }

    @PutMapping("/update/guest")
    @Operation(summary = "Update a guest")
    fun updateGuest(@RequestBody @Valid client: Guest): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.updateGuest(client))
    }

    @DeleteMapping("/delete/guest/{cpf}")
    @Operation(summary = "Delete a guest")
    fun deleteGuest(@PathVariable(name = "cpf") cpf: String): ResponseEntity<Void> {
        guestService.deleteGuestByCPF(cpf)
        return ResponseEntity.ok().build()
    }
}
