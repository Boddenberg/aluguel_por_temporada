package JuninWins.Project.enums

enum class AccommodationEnum (val descricao: String) {
    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    POUSADA("Pousada"),
    HOTEL("Hotel"),
    FAZENDA("Hotel Fazenda");

    companion object {
        fun fromDescricao(descricao: String): AccommodationEnum? =
            AccommodationEnum.values().find { it.descricao == descricao }
    }

    override fun toString(): String = descricao
}