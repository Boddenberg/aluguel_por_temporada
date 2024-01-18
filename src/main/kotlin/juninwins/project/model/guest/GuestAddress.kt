package juninwins.project.model.guest

import jakarta.persistence.*

@Entity
@Table(name = "tb_endereco_guest")
data class GuestAddress(
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
