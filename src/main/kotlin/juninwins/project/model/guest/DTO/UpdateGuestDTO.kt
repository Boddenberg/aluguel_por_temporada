package juninwins.project.model.guest.DTO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import juninwins.project.model.address.Address

data class UpdateGuestDTO(
    val cpf: String,
    @field:Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    val name: String? = null,
    @field:Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
    val lastName: String? = null,
    @field:Email(message = "E-mail inválido")
    val email: String? = null,
    val phoneNumber: String? = null,
    val birthDate: String? = null,
    val responsible: Boolean? = null,
    val host: Boolean? = null,
    val address: Address? = null
)