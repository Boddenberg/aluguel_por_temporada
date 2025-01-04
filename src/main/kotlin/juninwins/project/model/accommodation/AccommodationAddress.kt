package juninwins.project.model.accommodation
import jakarta.persistence.*

import jakarta.persistence.*


data class AccommodationAddress(

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
