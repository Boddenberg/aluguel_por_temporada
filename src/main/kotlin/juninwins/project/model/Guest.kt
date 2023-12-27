package juninwins.project.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.br.CPF
import org.springframework.format.annotation.DateTimeFormat

@Entity
@Table(name = "tb_cliente")
data class Guest(
    @Id
    @Column(
        name = "cpf",
        unique = true
    )
    @field:CPF
    var cpf: String,
    @Column(name = "nome")
    var name: String,
    @Column(name = "sobrenome")
    var lastName: String,
    @field:Email
    @Column(name = "email")
    var email: String,
    @Column(name = "telefone")
    var phoneNumber: String,
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "data_nascimento")
    var birthDate: String,
    @Column(name = "responsavel")
    var responsible: Boolean,
    @Column(name = "anfitriao")
    var host: Boolean,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    var address: Address
)