package JuninWins.Project.model

import JuninWins.Project.enums.StatusReservaEnum
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tb_reserva")
data class Booking(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "hospedagem_id")
    val accommodation: Accommodation,
    @Column(name = "data_inicio")
    val startDate: LocalDate,
    @Column(name = "data_fim")
    val endDate: LocalDate,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cliente_cpf")
    val guest: Guest,
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: StatusReservaEnum
) {
    constructor(CPFGuest: Guest, AccommodationId: Accommodation, startDate: LocalDate, endDate: LocalDate, status: StatusReservaEnum) :
            this(0, Accommodation(), startDate, endDate, Guest(), status) {
    }

    constructor() : this(null, Accommodation(), LocalDate.now(), LocalDate.now(), Guest(), StatusReservaEnum.PENDING)


}



/*
    O preço final da reserva substitui o preço base da hospedagem.
    O preço visível para o hóspede é apenas o preço da RESERVA.

 */