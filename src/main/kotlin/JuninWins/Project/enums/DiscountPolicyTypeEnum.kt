package JuninWins.Project.enums

enum class DiscountPolicyTypeEnum (val descricao: String) {
    NONE("Acomodação sem políticas de desconto"),
    WEEKLY("Política de desconto semanal"),
    BIWEEKLY("Política de desconto quinzenal"),
    MONTHLY("Política de desconto mensal"),
    CUSTOMIZED("Política de desconto customizada");

    companion object {
        fun fromDescricao(descricao: String): StatusReservaEnum? =
            StatusReservaEnum.values().find { it.descricao == descricao }
    }
    override fun toString(): String = descricao
}