package juninwins.project.model

import juninwins.project.enums.StatusReservaEnum
import jakarta.persistence.*
import java.time.LocalDate

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@Entity
@Table(name = "tb_reserva")
@JsonPropertyOrder("id", "startDate", "endDate", "status", "bookingDuration", "totalPrice", "guest", "host", "accommodation")
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
    @OneToOne
    @JoinColumn(name = "cliente_cpf")
    val guest: Guest,
    @OneToOne
    @JoinColumn(name = "anfitriao_cpf")
    val host: Guest,
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: StatusReservaEnum
) {
    // Construtores e outras propriedades...

    constructor(
        accommodation: Accommodation,
        startDate: LocalDate,
        endDate: LocalDate,
        bookingDuration: Int,
        totalPrice: Double,
        guest: Guest,
        host: Guest,
        status: StatusReservaEnum
    ) : this(0, accommodation, startDate, endDate, bookingDuration, totalPrice, guest, host, status)
}

/*
    O preço final da reserva substitui o preço base da hospedagem.
    O preço visível para o hóspede é apenas o preço da RESERVA.

 */