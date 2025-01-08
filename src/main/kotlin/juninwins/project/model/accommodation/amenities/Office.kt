package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Office(
        @get:DynamoDbAttribute("dedicatedWorkspace")
        var dedicatedWorkspace: Boolean? = null,

        @get:DynamoDbAttribute("wifi")
        var wifi: Boolean? = null,

        @get:DynamoDbAttribute("portableWifi")
        var portableWifi: Boolean? = null,

        @get:DynamoDbAttribute("printer")
        var printer: Boolean? = null
)
