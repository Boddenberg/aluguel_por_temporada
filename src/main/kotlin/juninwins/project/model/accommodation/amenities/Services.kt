package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Services(
        @get:DynamoDbAttribute("breakfast")
        var breakfast: Boolean?,

        @get:DynamoDbAttribute("longTermStaysAllowed")
        var longTermStaysAllowed: Boolean?,

        @get:DynamoDbAttribute("cleaningDuringStay")
        var cleaningDuringStay: Boolean?,

        @get:DynamoDbAttribute("luggageDropoffAllowed")
        var luggageDropoffAllowed: Boolean?
)