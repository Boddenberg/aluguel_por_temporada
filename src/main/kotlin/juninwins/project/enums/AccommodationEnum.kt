package juninwins.project.enums

enum class AccommodationEnum (val descricao: String) {
    HOUSE("Casa"),
    APARTMENT("Apartamento"),
    INN("Pousada"),
    HOTEL("Hotel"),
    FAZEND_HOTEL("Hotel Fazenda");

    companion object {
        fun fromDescricao(descricao: String): AccommodationEnum? =
            AccommodationEnum.values().find { it.descricao == descricao }
    }

    override fun toString(): String = descricao
}