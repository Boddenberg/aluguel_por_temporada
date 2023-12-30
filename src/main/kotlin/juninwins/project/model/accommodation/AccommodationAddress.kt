package juninwins.project.model.accommodation

import jakarta.persistence.*

@Entity
@Table(name = "tb_endereco_acomodacao")
data class AccommodationAddress(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null, // Adicione um ID para o endereço
        val logradouro: String,
        val numero: String,
        val complemento: String?,
        val bairro: String,
        val localidade: String,
        val uf: String,
        val cep: String
) {
    constructor() : this(0, "", "", null, "", "", "", "")
}
