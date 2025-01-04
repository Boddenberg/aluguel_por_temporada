package juninwins.project.model.guest

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import juninwins.project.model.address.Address
import org.hibernate.validator.constraints.br.CPF
import org.hibernate.validator.constraints.br.TituloEleitoral
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey


data class Guest(

    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("cpf")
    @field:CPF(message = "CPF inválido")
    @field:NotBlank(message = "CPF é obrigatório")
    var cpf: String = "",

    @get:DynamoDbAttribute("name")
    @field:NotBlank(message = "Nome é obrigatório")
    var name: String = "",

    @get:DynamoDbAttribute("lastName")
    @field:NotBlank(message = "Sobrenome é obrigatório")
    var lastName: String = "",

    @get:DynamoDbAttribute("email")
    @field:Email(message = "E-mail inválido")
    @field:NotBlank(message = "E-mail é obrigatório")
    var email: String = "",

    @get:DynamoDbAttribute("phoneNumber")
    @field:NotBlank(message = "Telefone é obrigatório")
    var phoneNumber: String = "",

    @get:DynamoDbAttribute("birthDate")
    @field:NotBlank(message = "Data de nascimento é obrigatória")
    var birthDate: String = "",

    @get:DynamoDbAttribute("responsible")
    var responsible: Boolean? = false,

    @get:DynamoDbAttribute("host")
    var host: Boolean? = false,

    @get:DynamoDbAttribute("address")
    @field:Valid
    var address: Address? = null
)
