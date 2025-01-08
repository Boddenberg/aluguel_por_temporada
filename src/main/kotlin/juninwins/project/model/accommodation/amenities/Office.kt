package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Office(
        @get:DynamoDbAttribute("dedicatedWorkspace")
        var dedicatedWorkspace: Boolean?,

        @get:DynamoDbAttribute("wifi")
        var wifi: Boolean?,

        @get:DynamoDbAttribute("portableWifi")
        var portableWifi: Boolean?,

        @get:DynamoDbAttribute("printer")
        var printer: Boolean?
)