package juninwins.project.model.accommodation

import jakarta.persistence.*



class DiscountPolicy(

    var policyType: String, // usar DiscountPolicyTypeEnum

    val discountPercentage: Double
) {

    val id: Long = 0
}