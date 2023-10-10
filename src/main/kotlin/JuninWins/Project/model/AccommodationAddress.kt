package JuninWins.Project.model

class AccommodationAddress (

    val id: Long? = null,
    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String
) {
    constructor() : this(0, "", "", null, "", "", "", "")


}
