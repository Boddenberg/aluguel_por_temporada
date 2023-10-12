package JuninWins.Project.model

import jakarta.persistence.*


@Entity
@Table(name = "tb_politica_desconto")
class DiscountPolicy(
    @Column(name = "tipo_politica")
    var policyType: String, // usar DiscountPolicyTypeEnum
    @Column(name = "desconto")
    val discountPercentage: Double
) {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}