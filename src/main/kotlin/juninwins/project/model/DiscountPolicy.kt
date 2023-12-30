package juninwins.project.model

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

//could not execute statement [Table 'db.tb_hospedagem__discount_policy'
// doesn't exist] [insert into tb_hospedagem__discount_policy
// (accommodation_id,_discount_policy_id) values (?,?)];
// SQL [insert into tb_hospedagem__discount_policy
// (accommodation_id,_discount_policy_id) values (?,?)]