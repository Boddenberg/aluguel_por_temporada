package JuninWins.Project.model

import lombok.Data

@Data
data class Cliente(
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val dataNascimento: String,
    val cpf: String,
    val responsavel: Boolean,
    val endereco: Endereco
)