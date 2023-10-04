package JuninWins.Project.model

import jakarta.persistence.*

@Entity
data class Endereco(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Adicione um ID para o endereço
    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String
){
    constructor() : this(null, "", "", null, "", "", "", "")
}
