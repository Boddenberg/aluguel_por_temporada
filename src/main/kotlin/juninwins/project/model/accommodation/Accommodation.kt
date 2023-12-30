package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*
import juninwins.project.model.DiscountPolicy
import juninwins.project.model.Guest


@Entity
@Table(name = "tb_hospedagem")
@JsonPropertyOrder("id", "type", "localization", "capacity", "basePrice", "address", "discountPolicy")
@JsonIgnoreProperties
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
        @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_acomodacao_id")
    var address: AccommodationAddress
) {
    @OneToMany(cascade = [CascadeType.ALL])
    var _discountPolicy: List<DiscountPolicy> = ArrayList()

    fun addDiscountPolicy(discountPolicy: DiscountPolicy) {
        _discountPolicy += discountPolicy
    }
}
