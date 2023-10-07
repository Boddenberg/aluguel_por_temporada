package JuninWins.Project.enums

enum class StatusReservaEnum(val descricao: String) {
    PENDING("Reserva pendente"),
    CONFIRMED("Reserva confirmada"),
    IN_PROGRESS("Reserva em andamento"),
    CANCELED("Reserva cancelada"),
    CONCLUDED("Reserva concluída");

    companion object {
        fun fromDescricao(descricao: String): StatusReservaEnum? =
            values().find { it.descricao == descricao }
    }

    override fun toString(): String = descricao
}
