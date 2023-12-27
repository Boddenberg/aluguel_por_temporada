package juninwins.project.enums

enum class DiscountPolicyTypeEnum (val descricao: String) {
    NONE("Acomodação sem políticas de desconto"),
    WEEKLY("semanal"),
    BIWEEKLY("quinzenal"),
    MONTHLY("mensal"),
    CUSTOMIZED("customizada");

    companion object {
        fun fromDescricao(descricao: String): DiscountPolicyTypeEnum? =
            DiscountPolicyTypeEnum.values().find { it.descricao == descricao }
    }
    override fun toString(): String = descricao
}