package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Basics(
        @get:DynamoDbAttribute("centralHeating")
        val centralHeating: Boolean? = null,

        @get:DynamoDbAttribute("generalAirConditioning")
        val generalAirConditioning: Boolean? = null,

        @get:DynamoDbAttribute("dryer")
        val dryer: Boolean? = null,

        @get:DynamoDbAttribute("generalWifi")
        val generalWifi: Boolean? = null
)
