package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import juninwins.project.model.review.ReviewByGuest


@JsonPropertyOrder("id", "type", "localization", "capacity", "basePrice", "address", "discountPolicy", "guest")
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
class Accommodation(

        val id: Long?,

        var type: String, // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc

        var localization: String?,

        var capacity: Int = 0, // até quantas pessoas podem se hospedar na acomodação

        var basePrice: Double,

        var amenities: Amenities?,


        var reviews: MutableList<ReviewByGuest>? = mutableListOf(),

        var address: AccommodationAddress
) {

    var _discountPolicy: List<DiscountPolicy> = ArrayList()

    fun addDiscountPolicy(discountPolicy: DiscountPolicy) {
        _discountPolicy += discountPolicy
    }
}
