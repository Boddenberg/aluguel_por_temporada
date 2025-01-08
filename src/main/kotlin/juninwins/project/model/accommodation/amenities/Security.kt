package juninwins.project.model.accommodation.amenities


import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Security(
        @get:DynamoDbAttribute("carbonMonoxideAlarm")
        var carbonMonoxideAlarm: Boolean?,

        @get:DynamoDbAttribute("smokeDetector")
        var smokeDetector: Boolean?,

        @get:DynamoDbAttribute("fireExtinguisher")
        var fireExtinguisher: Boolean?,

        @get:DynamoDbAttribute("firstAidKit")
        var firstAidKit: Boolean?
)
