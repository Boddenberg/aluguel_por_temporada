package JuninWins.Project.model

import jakarta.persistence.*


@Entity
@Table(name = "tb_politica_desconto")
data class DiscountPolicy(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "tipo_politica")
    val policyType: String? = null, // Pode ser 'semanal', 'feriado', etc.
    @Column(name = "desconto")
    val discountPercentage: Double? = null,
    @Column(name = "hospedagem_id")
    val accommodationId: Int? = null
) {
    constructor() : this(0, "", 0.0, 0)
}