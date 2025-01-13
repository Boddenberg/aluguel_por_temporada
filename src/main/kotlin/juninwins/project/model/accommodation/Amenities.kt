package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonInclude
import juninwins.project.model.accommodation.amenities.*
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
data class Amenities(
        @get:DynamoDbAttribute("basics")
        val basics: Basics? = null,

        @get:DynamoDbAttribute("bathroom")
        val bathroom: Bathroom? = null,

        @get:DynamoDbAttribute("bedroom")
        val bedroom: Bedroom? = null,

        @get:DynamoDbAttribute("entertainment")
        val entertainment: Entertainment? = null,

        @get:DynamoDbAttribute("family")
        val family: Family? = null,

        @get:DynamoDbAttribute("diningRoom")
        val diningRoom: Kitchen? = null,

        @get:DynamoDbAttribute("laundry")
        val laundry: Laundry? = null,

        @get:DynamoDbAttribute("location")
        val location: Location? = null,

        @get:DynamoDbAttribute("office")
        val office: Office? = null,

        @get:DynamoDbAttribute("parking")
        val parking: Parking? = null,

        @get:DynamoDbAttribute("security")
        val security: Security? = null,

        @get:DynamoDbAttribute("services")
        val services: Services? = null,

        @get:DynamoDbAttribute("streaming")
        val streaming: Streaming? = null
)
