package JuninWins.Project.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "tb_cliente")
data class Cliente(
    @Id
    val id: Long,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val telefone: String,
    val dataNascimento: String,
    val cpf: String,
    val responsavel: Boolean,
    val endereco: Endereco
)