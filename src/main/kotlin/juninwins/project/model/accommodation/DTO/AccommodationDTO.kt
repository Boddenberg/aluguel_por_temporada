package juninwins.project.model.accommodation.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import juninwins.project.model.accommodation.Amenities
import juninwins.project.model.address.Address
import juninwins.project.model.review.ReviewByGuest

data class AccommodationDTO(

    @field:NotBlank(message = "Id é obrigatório")
    val id: String = "",

    @field:NotBlank(message = "Type é obrigatório")
    val type: String = "", // se é uma casa, apartamento, cabana, pousada, cabana, iglu, etc

    @field:Positive(message = "Capacity deve ser um número positivo")
    val capacity: Int = 0, // até quantas pessoas podem se hospedar na acomodação

    @field:NotNull(message = "Base price é obrigatório")
    val basePrice: Double? = null,

    val amenities: Amenities? = null,

    val reviews: MutableList<ReviewByGuest>? = null,

    val address: Address? = null
)
