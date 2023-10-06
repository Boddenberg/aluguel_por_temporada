package JuninWins.Project.enums

enum class StatusReserva(val descricao: String) {
    PENDENTE("Pendente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    CONCLUIDA("Concluída");

    companion object {
        fun fromDescricao(descricao: String): StatusReserva? =
            values().find { it.descricao == descricao }
    }

    override fun toString(): String = descricao
}
