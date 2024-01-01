import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.DiscountPolicy
import juninwins.project.utils.formatDouble
import java.time.LocalDate
import java.time.Period

// classses que não precisam manter nenhum estado mutável e são somente baseadas em lógicas e cálculos devem ser objects
object BookingUtils {

    fun calculateBookingDurationDays(startDate: LocalDate, endDate: LocalDate): Int {
        val period = Period.between(startDate, endDate)
        return period.days + period.months * 30 + period.years * 365
    }

    fun calculateBookingTotalPrice(basePrice: Double, bookingDuration: Int, accommodation: Accommodation): Double {
        var totalPrice = basePrice * bookingDuration
        val appliedDiscountPolicy = findApplicableDiscountPolicy(accommodation, bookingDuration)

        if (appliedDiscountPolicy != null) {
            totalPrice *= (1.0 - appliedDiscountPolicy.discountPercentage / 100.0)
        }

        return formatDouble(totalPrice)
    }

    private fun findApplicableDiscountPolicy(accommodation: Accommodation, duracaoReserva: Int): DiscountPolicy? {
        var appliedDiscountPolicy: DiscountPolicy? = null

        for (discountPolicy in accommodation._discountPolicy) {
            appliedDiscountPolicy = handlePolicy(discountPolicy, appliedDiscountPolicy, duracaoReserva)
        }

        return appliedDiscountPolicy
    }

    private fun handlePolicy(policy: DiscountPolicy, currentPolicy: DiscountPolicy?, duracaoReserva: Int): DiscountPolicy? {
        when (policy.policyType) {
            "semanal" -> {
                if (duracaoReserva > 7 && (currentPolicy == null || currentPolicy.policyType != "quinzenal")) {
                    return policy
                }
            }
            "quinzenal" -> {
                if (duracaoReserva > 14 && (currentPolicy == null || currentPolicy.policyType != "mensal")) {
                    return policy
                }
            }
            "mensal" -> {
                if (duracaoReserva >= 30) {
                    return policy
                }
            }
        }
        return currentPolicy
    }
}
