package JuninWins.Project.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.Data

@Data
@Entity
@Table(name = "tb_cliente")
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