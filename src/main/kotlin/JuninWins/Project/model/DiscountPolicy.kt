package JuninWins.Project.model

import JuninWins.Project.enums.DiscountPolicyTypeEnum
import jakarta.persistence.*


data class DiscountPolicy(

    val policyType: DiscountPolicyTypeEnum,
    val discountPercentage: Double
)