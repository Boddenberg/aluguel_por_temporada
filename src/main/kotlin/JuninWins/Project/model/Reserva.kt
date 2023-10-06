package JuninWins.Project.model

import JuninWins.Project.enums.StatusReservaEnum
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tb_reserva")
data class Reserva(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cliente_cpf")
    val cliente: Cliente,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "hospedagem_id")
    val hospedagem: Accommodation,
    @Column(name = "data_inicio")
    val dataInicio: LocalDate,
    @Column(name = "data_fim")
    val dataFim: LocalDate,
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: StatusReservaEnum
) {
    constructor() : this(0, Cliente(), Accommodation(), LocalDate.now(), LocalDate.now(), StatusReservaEnum.PENDENTE)
}