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
class GuestController (val guestService: GuestService) {

    @GetMapping("/search/{cpf}")
    fun findGuest(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<Guest> {
        return ResponseEntity.ok(guestService.findGuestByCPF(cpfCustomer))
    }

    @GetMapping("/searchAll")
    fun findGuest(): List<Guest> {
        return guestService.findAllGuests()
    }

    @PostMapping("/register/guest")
    @Operation(summary = "Register a guest")
    fun saveGuests(@RequestBody @Valid cliente: Guest): ResponseEntity<Void> {
        guestService.save(cliente)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    fun getDynamoDbTable(client: DynamoDbClient): DynamoDbTable<Guest> {
        val enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(client)
            .build()

        return enhancedClient.table("Guest", TableSchema.fromBean(Guest::class.java))
    }

    fun createDynamoDbClient(): DynamoDbClient {
        return DynamoDbClient.builder()
            .region(Region.US_EAST_1)
            .build()
    }

}
