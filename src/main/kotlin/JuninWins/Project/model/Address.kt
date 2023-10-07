package JuninWins.Project.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_endereco")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val street: String,
    val number: String,
    val complement: String?,
    val neighborhood: String,
    val city: String,
    val state: String,
    val zipCode: String
) {
    constructor() : this(null, "", "", null, "", "", "", "")
}
