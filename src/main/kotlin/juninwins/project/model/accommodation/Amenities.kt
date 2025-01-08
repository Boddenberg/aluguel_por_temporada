package juninwins.project.model.accommodation

import com.fasterxml.jackson.annotation.JsonInclude
import juninwins.project.model.accommodation.amenities.*
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute


@JsonInclude(JsonInclude.Include.NON_NULL)
data class Amenities(
        @get:DynamoDbAttribute("basics")
        val basics: Basics?,

        @get:DynamoDbAttribute("bathroom")
        val bathroom: Bathroom?,

        @get:DynamoDbAttribute("bedroom")
        val bedroom: Bedroom?,

        @get:DynamoDbAttribute("entertainment")
        val entertainment: Entertainment?,

        @get:DynamoDbAttribute("family")
        val family: Family?,

        @get:DynamoDbAttribute("diningRoom")
        val diningRoom: Kitchen?,

        @get:DynamoDbAttribute("laundry")
        val laundry: Laundry?,

        @get:DynamoDbAttribute("location")
        val location: Location?,

        @get:DynamoDbAttribute("office")
        val office: Office?,

        @get:DynamoDbAttribute("parking")
        val parking: Parking?,

        @get:DynamoDbAttribute("security")
        val security: Security?,

        @get:DynamoDbAttribute("services")
        val services: Services?,

        @get:DynamoDbAttribute("streaming")
        val streaming: Streaming?
)
