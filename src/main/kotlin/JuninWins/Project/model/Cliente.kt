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
    var nome: String,
    var sobrenome: String,
    var email: String,
    var telefone: String,
    var dataNascimento: String,
    var cpf: String,
    var responsavel: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    var endereco: Endereco
) {
    constructor() : this(0, "","", "", "", "", "", false, Endereco())
}