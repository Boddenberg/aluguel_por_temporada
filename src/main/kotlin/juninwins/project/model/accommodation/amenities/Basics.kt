package juninwins.project.model.accommodation.amenities


import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Basics (
        @get:DynamoDbAttribute("centralHeating")
        val centralHeating: Boolean?,

        @get:DynamoDbAttribute("generalAirConditioning")
        val generalAirConditioning: Boolean?,

        @get:DynamoDbAttribute("dryer")
        val dryer: Boolean?,

        @get:DynamoDbAttribute("generalWifi")
        val generalWifi: Boolean?
)