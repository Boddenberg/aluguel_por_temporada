package JuninWins.Project.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.br.CPF
import org.springframework.format.annotation.DateTimeFormat

@Entity
@Table(name = "tb_cliente")
data class Cliente(
    @Id
    @Column(
        name = "cpf",
        unique = true
    )
    @CPF
    var cpf: String,
    @Column(name = "nome")
    var nome: String,
    @Column(name = "sobrenome")
    var sobrenome: String,
    @Email
    @Column(name = "email")
    var email: String,
    @Column(name = "telefone")
    var telefone: String,
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "data_nascimento")
    var dataNascimento: String,
    @Column(name = "responsavel")
    var responsavel: Boolean,
    @Column(name = "anfitriao")
    var anfitriao: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    var endereco: Endereco
) {
    constructor() : this("", "", "", "", "", "", false, false, Endereco())
}