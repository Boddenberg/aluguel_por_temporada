package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bathroom(
        @get:DynamoDbAttribute("bathtub")
        val bathtub: Boolean?,

        @get:DynamoDbAttribute("bidet")
        val bidet: Boolean?,

        @get:DynamoDbAttribute("outdoorShower")
        val outdoorShower: Boolean?,

        @get:DynamoDbAttribute("conditioner")
        val conditioner: Boolean?,

        @get:DynamoDbAttribute("showerGel")
        val showerGel: Boolean?,

        @get:DynamoDbAttribute("cleaningProducts")
        val cleaningProducts: Boolean?,

        @get:DynamoDbAttribute("bodySoap")
        val bodySoap: Boolean?,

        @get:DynamoDbAttribute("hairDryer")
        val hairDryer: Boolean?,

        @get:DynamoDbAttribute("shampoo")
        val shampoo: Boolean?,

        @get:DynamoDbAttribute("hotWater")
        val hotWater: Boolean?
)

