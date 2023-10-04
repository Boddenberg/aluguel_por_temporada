package JuninWins.Project.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import lombok.Data
import org.hibernate.validator.constraints.br.CPF
import org.springframework.format.annotation.DateTimeFormat

@Data
@Entity
@Table(name = "tb_cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var nome: String,
    var sobrenome: String,
    @Email
    var email: String,
    var telefone: String,
    @DateTimeFormat
    var dataNascimento: String,
    @CPF
    var cpf: String,
    var responsavel: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    var endereco: Endereco
) {
    constructor() : this(0, "","", "", "", "", "", false, Endereco())
}