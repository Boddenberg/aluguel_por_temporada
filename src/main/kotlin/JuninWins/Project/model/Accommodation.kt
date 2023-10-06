package JuninWins.Project.model

import JuninWins.Project.enums.AccommodationEnum
import jakarta.persistence.*

@Entity
@Table(name = "tb_hospedagem")
data class Accommodation(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "tipo")
    val tipo: String, // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc
    @Column(name = "localizacao")
    val localizacao: String?,
    @Column(name = "capacidade")
    val capacidade: Int = 0, //até quantas pessoas podem se hospedar na acomodação
    @Column(name = "preco_por_noite")
    val precoPorNoite: Double, // como implementar desconto? tipo desconto para reservas de 7 dias+, 30 dias+ e aumento de preço quando for feriado e tal....?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "endereco_id")
    val endereco: Endereco
) {
    constructor() : this(0, "", "", 0, Double.MIN_VALUE, Endereco())
}