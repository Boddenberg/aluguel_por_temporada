package JuninWins.Project.enums

enum class StatusReservaEnum(val descricao: String) {
    PENDENTE("Pendente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    CONCLUIDA("Concluída");

    companion object {
        fun fromDescricao(descricao: String): StatusReservaEnum? =
            values().find { it.descricao == descricao }
    }

    override fun toString(): String = descricao
}
