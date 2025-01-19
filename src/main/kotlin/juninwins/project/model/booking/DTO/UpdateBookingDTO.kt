package juninwins.project.model.booking.DTO

import jakarta.validation.constraints.NotBlank
import juninwins.project.enums.StatusReservaEnum
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.guest.Guest
import java.time.LocalDate

data class UpdateBookingDTO(
    @field:NotBlank(message = "O ID é obrigatório")
    val id: String,
    val accommodation: Accommodation? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val bookingDuration: Int? = null,
    val totalPrice: Double? = null,
    val reviwedByGuest: Boolean? = null,
    val reviwedByHost: Boolean? = null,
    val guest: Guest? = null,
    val host: Guest? = null,
    val status: StatusReservaEnum? = null,
    val reviewStatus: StatusReservaEnum? = null
)