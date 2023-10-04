package JuninWins.Project.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "tb_cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val dataNascimento: String,
    val cpf: String,
    val responsavel: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    val endereco: Endereco
) {
    constructor() : this(0, "", "", "", "", "", false, Endereco())
}