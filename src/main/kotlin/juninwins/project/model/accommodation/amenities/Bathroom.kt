package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bathroom(
        @get:DynamoDbAttribute("bathtub")
        val bathtub: Boolean? = null,

        @get:DynamoDbAttribute("bidet")
        val bidet: Boolean? = null,

        @get:DynamoDbAttribute("outdoorShower")
        val outdoorShower: Boolean? = null,

        @get:DynamoDbAttribute("conditioner")
        val conditioner: Boolean? = null,

        @get:DynamoDbAttribute("showerGel")
        val showerGel: Boolean? = null,

        @get:DynamoDbAttribute("cleaningProducts")
        val cleaningProducts: Boolean? = null,

        @get:DynamoDbAttribute("bodySoap")
        val bodySoap: Boolean? = null,

        @get:DynamoDbAttribute("hairDryer")
        val hairDryer: Boolean? = null,

        @get:DynamoDbAttribute("shampoo")
        val shampoo: Boolean? = null,

        @get:DynamoDbAttribute("hotWater")
        val hotWater: Boolean? = null
)
