package JuninWins.Project.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_hospedagem")
data class Accommodation(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "tipo")
    val type: String, // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc
    @Column(name = "localizacao")
    val localization: String?,
    @Column(name = "capacidade")
    val capacity: Int = 0, //até quantas pessoas podem se hospedar na acomodação
    @Column(name = "preco_por_noite")
    val basePrice: Double, // como implementar desconto? tipo desconto para reservas de 7 dias+, 30 dias+ e aumento de preço quando for feriado e tal....?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    val address: Address
) {
    constructor() : this(0, "", "", 0, Double.MIN_VALUE, Address())
}

/*
    O que compõe o preço:
        preço base
        descontos
        quantidade de hóspedes
        quantidade de dias
        taxas - pet, taxa de limpeza, taxa de danos
 */