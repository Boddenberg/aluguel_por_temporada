package juninwins.project.model.guest

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import juninwins.project.model.address.Address
import org.hibernate.validator.constraints.br.CPF
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbFlatten
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
data class Guest(

    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("cpf")
    @field:CPF(message = "CPF inválido")
    @field:NotBlank(message = "CPF é obrigatório")
    var cpf: String = "",

    @get:DynamoDbAttribute("name")
    @field:Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    @field:NotBlank(message = "Nome é obrigatório")
    var name: String = "",

    @get:DynamoDbAttribute("lastName")
    @field:Size(min = 2, max = 50, message = "O sobrenome deve ter entre 2 e 50 caracteres")
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

    var address: Address? = null
)
