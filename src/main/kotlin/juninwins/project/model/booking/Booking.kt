package juninwins.project.model.booking

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import juninwins.project.enums.StatusReservaEnum
import juninwins.project.model.guest.Guest
import juninwins.project.model.accommodation.Accommodation
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
@JsonPropertyOrder("id", "startDate", "endDate", "status", "bookingDuration", "totalPrice", "reviewStatus", "reviwedByGuest", "reviwedByHost", "guest", "host", "accommodation")
data class Booking(

        @get:DynamoDbPartitionKey
        @get:DynamoDbAttribute("id")
        @field:NotBlank(message = "ID da reserva é obrigatório")
        var id: String = "",

        @get:DynamoDbAttribute("accommodation")
        @field:Valid
        val accommodation: Accommodation,

        @get:DynamoDbAttribute("startDate")
        @field:NotBlank(message = "Data de início é obrigatória")
        val startDate: LocalDate,

        @get:DynamoDbAttribute("endDate")
        @field:NotBlank(message = "Data de término é obrigatória")
        val endDate: LocalDate,

        @get:DynamoDbAttribute("bookingDuration")
        var bookingDuration: Int,

        @get:DynamoDbAttribute("totalPrice")
        var totalPrice: Double,

        @get:DynamoDbAttribute("reviwedByGuest")
        var reviwedByGuest: Boolean? = false,

        @get:DynamoDbAttribute("reviwedByHost")
        var reviwedByHost: Boolean? = false,

        @get:DynamoDbAttribute("guest")
        @field:Valid
        val guest: Guest,

        @get:DynamoDbAttribute("host")
        @field:Valid
        val host: Guest,

        @get:DynamoDbAttribute("status")
        val status: StatusReservaEnum,

        @get:DynamoDbAttribute("reviewStatus")
        val reviewStatus: StatusReservaEnum?
)
