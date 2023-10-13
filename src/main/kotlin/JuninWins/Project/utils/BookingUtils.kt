import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.DiscountPolicy
import JuninWins.Project.utils.NumberUtils
import java.time.LocalDate

// classses que não precisam manter nenhum estado mutável e são somente baseadas em lógicas e cálculos devem ser objects
object BookingUtils {

    fun calculateBookingDurationDays(startDate: LocalDate, endDate: LocalDate): Int {
        return startDate.until(endDate).days
    }

    fun calculateBookingTotalPrice(basePrice: Double, bookingDuration: Int, accommodation: Accommodation): Double {
        var totalPrice = basePrice * bookingDuration
        val appliedDiscountPolicy = findApplicableDiscountPolicy(accommodation, bookingDuration)

        if (appliedDiscountPolicy != null) {
            totalPrice *= (1.0 - appliedDiscountPolicy.discountPercentage / 100.0)
        }

        return NumberUtils.formatDouble(totalPrice)
    }

    private fun findApplicableDiscountPolicy(accommodation: Accommodation, duracaoReserva: Int): DiscountPolicy? {
        var appliedDiscountPolicy: DiscountPolicy? = null

        for (discountPolicy in accommodation._discountPolicy) {
            appliedDiscountPolicy = applyPolicy(discountPolicy, appliedDiscountPolicy, duracaoReserva)
        }

        return appliedDiscountPolicy
    }

    private fun applyPolicy(policy: DiscountPolicy, currentPolicy: DiscountPolicy?, duracaoReserva: Int): DiscountPolicy? {
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
            // TODO: Política customizada e mudar políticas para enums
        }

        return currentPolicy
    }
}
