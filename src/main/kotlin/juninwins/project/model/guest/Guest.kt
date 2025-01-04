package juninwins.project.model.guest

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Email
import juninwins.project.model.address.Address
import org.springframework.format.annotation.DateTimeFormat
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey


@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean()
data class Guest(

    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("cpf")
    var cpf: String = "",

    @get:DynamoDbAttribute("name")
    var name: String = "",

    @get:DynamoDbAttribute("lastName")
    var lastName: String = "",

    @get:DynamoDbAttribute("email")
    @field:Email
    var email: String = "",

    @get:DynamoDbAttribute("phoneNumber")
    var phoneNumber: String = "",

    @get:DynamoDbAttribute("birthDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    var birthDate: String = "",

    @get:DynamoDbAttribute("responsible")
    var responsible: Boolean? = false,

    @get:DynamoDbAttribute("host")
    var host: Boolean? = false,

    @get:DynamoDbAttribute("address")
    var address: Address? = null
)