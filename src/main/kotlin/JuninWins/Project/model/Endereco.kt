package JuninWins.Project.model

import jakarta.persistence.Embeddable

@Embeddable
data class Endereco(
    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String
)
