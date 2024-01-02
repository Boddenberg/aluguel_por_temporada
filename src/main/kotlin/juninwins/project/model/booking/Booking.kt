package juninwins.project.model.booking

import juninwins.project.enums.StatusReservaEnum
import jakarta.persistence.*
import java.time.LocalDate

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import juninwins.project.model.guest.Guest
import juninwins.project.model.accommodation.Accommodation

@Entity
@Table(name = "tb_reserva")
@JsonPropertyOrder("id", "startDate", "endDate", "status", "bookingDuration", "totalPrice", "reviewStatus", "reviwedByGuest", "reviwedByHost", "guest", "host", "accommodation")
data class Booking(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @OneToOne
        @JoinColumn(name = "hospedagem_id")
        val accommodation: Accommodation,
        @Column(name = "data_inicio")
        val startDate: LocalDate,
        @Column(name = "data_fim")
        val endDate: LocalDate,
        @Column(name = "duracao_reserva")
        var bookingDuration: Int,
        @Column(name = "preco_total")
        val totalPrice: Double,
        @Column(name = "reviewed_by_guest", insertable = false, updatable = false)
        val reviwedByGuest: Boolean? = false,
        @Column(name = "reviewed_by_host", insertable = false, updatable = false)
        val reviwedByHost: Boolean? = false,
        @OneToOne
        @JoinColumn(name = "cliente_cpf")
        val guest: Guest,
        @OneToOne
        @JoinColumn(name = "anfitriao_cpf")
        val host: Guest,
        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        val status: StatusReservaEnum,
        @Column(name = "review_status")
        @Enumerated(EnumType.STRING)
        val reviewStatus: StatusReservaEnum?
) {

    constructor(
            accommodation: Accommodation,
            startDate: LocalDate,
            endDate: LocalDate,
            bookingDuration: Int,
            totalPrice: Double,
            reviwedByGuest: Boolean? = false,
            reviwedByHost: Boolean? = false,
            guest: Guest,
            host: Guest,
            status: StatusReservaEnum,
            reviewStatus: StatusReservaEnum?
    ) : this(0, accommodation, startDate, endDate, bookingDuration, totalPrice, reviwedByGuest, reviwedByHost, guest, host, status, reviewStatus)
}
