package JuninWins.Project.model

import JuninWins.Project.enums.StatusReserva
import java.time.LocalDate

data class Reserva(
    val id: Long,
    val cliente: Cliente,
    val hospedagem: Hospedagem,
    val dataInicio: LocalDate,
    val dataFim: LocalDate,
    val status: StatusReserva
)