package juninwins.project.model.booking

import juninwins.project.enums.StatusReservaEnum
import java.time.LocalDate

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.accommodation.Accommodation


@JsonPropertyOrder("id", "startDate", "endDate", "status", "bookingDuration", "totalPrice", "reviewStatus", "reviwedByGuest", "reviwedByHost", "guest", "host", "accommodation")
data class Booking(

        val accommodation: Accommodation,

        val startDate: LocalDate,

        val endDate: LocalDate,

        var bookingDuration: Int,

        val totalPrice: Double,

        var reviwedByGuest: Boolean? = false,

        var reviwedByHost: Boolean? = false,

        val guestComplete: GuestComplete,

        val host: GuestComplete,

        val status: StatusReservaEnum,

        val reviewStatus: StatusReservaEnum?
) {

}
