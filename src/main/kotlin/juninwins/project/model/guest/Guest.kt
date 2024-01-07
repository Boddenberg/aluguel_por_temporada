package juninwins.project.model.guest

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import juninwins.project.model.review.ReviewByHost
import org.springframework.format.annotation.DateTimeFormat

@Entity
@Table(name = "tb_cliente")
data class Guest(
        @Id
    @Column(
        name = "cpf",
        unique = true
    )
//    @field:CPF
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
        @OneToMany(cascade = [CascadeType.ALL])
    var reviews: MutableList<ReviewByHost>? = mutableListOf(),
        @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    var address: GuestAddress
)