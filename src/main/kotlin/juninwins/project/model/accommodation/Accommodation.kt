package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*
import juninwins.project.model.review.ReviewByGuest
import juninwins.project.model.review.ReviewByHost


@Entity
@Table(name = "tb_hospedagem")
@JsonPropertyOrder("id", "type", "localization", "capacity", "basePrice", "address", "discountPolicy", "guest")
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
class Accommodation(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "tipo")
        var type: String, // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc
        @Column(name = "localizacao")
        var localization: String?,
        @Column(name = "capacidade")
        var capacity: Int = 0, // até quantas pessoas podem se hospedar na acomodação
        @Column(name = "preco_por_noite")
        var basePrice: Double,
        @Embedded
        @Column(name = "comodidades")
        var amenities: Amenities?,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "review_id", referencedColumnName = "id")
        var reviews: MutableList<ReviewByGuest>? = mutableListOf(),
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "endereco_id", referencedColumnName = "id")
        var address: AccommodationAddress
) {
    @OneToMany(cascade = [CascadeType.ALL])
    var _discountPolicy: List<DiscountPolicy> = ArrayList()

    fun addDiscountPolicy(discountPolicy: DiscountPolicy) {
        _discountPolicy += discountPolicy
    }
}
