package JuninWins.Project.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.persistence.*


@Entity
@Table(name = "tb_hospedagem")
@JsonPropertyOrder("id", "type", "localization", "capacity", "basePrice", "address", "discountPolicy", "guest")
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
    var basePrice: Double, // como implementar desconto? tipo desconto para reservas de 7 dias+, 30 dias+ e aumento de preço quando for feriado e tal....?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    var address: Address
) {
    @OneToMany(cascade = [CascadeType.ALL])
    var _discountPolicy: List<DiscountPolicy> = ArrayList() // Adicione um campo para a política de desconto

    fun addDiscountPolicy(discountPolicy: DiscountPolicy) {
        _discountPolicy += discountPolicy
    }
}
/*
    O que compõe o preço:
        preço base
        descontos
        quantidade de hóspedes
        quantidade de dias
        taxas - pet, taxa de limpeza, taxa de danos
 */