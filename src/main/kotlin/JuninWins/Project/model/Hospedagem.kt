package JuninWins.Project.model

data class Hospedagem(
    val id: Long,
    val tipo: String, // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc
    val localizacao: String?,
    val capacidade: Int = 0, //até quantas pessoas podem se hospedar na acomodação
    val precoPorNoite: Double, // como implementar desconto? tipo desconto para reservas de 7 dias+, 30 dias+ e aumento de preço quando for feriado e tal....?
    val endereco: Endereco
)