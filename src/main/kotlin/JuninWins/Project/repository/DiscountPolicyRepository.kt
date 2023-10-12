package JuninWins.Project.repository

import JuninWins.Project.model.DiscountPolicy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountPolicyRepository: JpaRepository<DiscountPolicy, Long>