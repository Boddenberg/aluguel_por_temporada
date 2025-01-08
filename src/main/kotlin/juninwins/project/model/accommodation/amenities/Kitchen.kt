package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Kitchen(
        @get:DynamoDbAttribute("bakingSheet")
        var bakingSheet: Boolean?,

        @get:DynamoDbAttribute("coffeeMaker")
        var coffeeMaker: Boolean?,

        @get:DynamoDbAttribute("coffee")
        var coffee: Boolean?,

        @get:DynamoDbAttribute("hotWaterKettle")
        var hotWaterKettle: Boolean?,

        @get:DynamoDbAttribute("kitchen")
        var kitchen: Boolean?,

        @get:DynamoDbAttribute("stove")
        var stove: Boolean?,

        @get:DynamoDbAttribute("oven")
        var oven: Boolean?,

        @get:DynamoDbAttribute("freezer")
        var freezer: Boolean?,

        @get:DynamoDbAttribute("minibar")
        var minibar: Boolean?,

        @get:DynamoDbAttribute("basicKitchenItems")
        var basicKitchenItems: Boolean?,

        @get:DynamoDbAttribute("kitchenette")
        var kitchenette: Boolean?,

        @get:DynamoDbAttribute("dishwasher")
        var dishwasher: Boolean?,

        @get:DynamoDbAttribute("blender")
        var blender: Boolean?,

        @get:DynamoDbAttribute("trashCompactor")
        var trashCompactor: Boolean?,

        @get:DynamoDbAttribute("dishesAndCutlery")
        var dishesAndCutlery: Boolean?,

        @get:DynamoDbAttribute("diningTable")
        var diningTable: Boolean?,

        @get:DynamoDbAttribute("microwave")
        var microwave: Boolean?,

        @get:DynamoDbAttribute("breadMachine")
        var breadMachine: Boolean?,

        @get:DynamoDbAttribute("riceCooker")
        var riceCooker: Boolean?,

        @get:DynamoDbAttribute("refrigerator")
        var refrigerator: Boolean?,

        @get:DynamoDbAttribute("wineGlasses")
        var wineGlasses: Boolean?,

        @get:DynamoDbAttribute("toaster")
        var toaster: Boolean?,

        @get:DynamoDbAttribute("barbecueUtensils")
        var barbecueUtensils: Boolean?
)
