import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Email
import juninwins.project.model.address.Address
import juninwins.project.model.review.Review
import org.springframework.format.annotation.DateTimeFormat
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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
    var responsible: Boolean? = false
) {

    fun checkIfResponsible() {

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val birthDateParsed = LocalDate.parse(birthDate, formatter)
        val currentDate = LocalDate.now()
        val age = ChronoUnit.YEARS.between(birthDateParsed, currentDate)
        responsible = age >= 18
    }
}
