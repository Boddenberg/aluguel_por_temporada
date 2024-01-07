package juninwins.project.repository

import juninwins.project.model.accommodation.DiscountPolicy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountPolicyRepository: JpaRepository<DiscountPolicy, Long>