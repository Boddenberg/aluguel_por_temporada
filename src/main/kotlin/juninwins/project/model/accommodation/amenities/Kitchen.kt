package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Kitchen(
        @get:DynamoDbAttribute("bakingSheet")
        var bakingSheet: Boolean? = null,

        @get:DynamoDbAttribute("coffeeMaker")
        var coffeeMaker: Boolean? = null,

        @get:DynamoDbAttribute("coffee")
        var coffee: Boolean? = null,

        @get:DynamoDbAttribute("hotWaterKettle")
        var hotWaterKettle: Boolean? = null,

        @get:DynamoDbAttribute("kitchen")
        var kitchen: Boolean? = null,

        @get:DynamoDbAttribute("stove")
        var stove: Boolean? = null,

        @get:DynamoDbAttribute("oven")
        var oven: Boolean? = null,

        @get:DynamoDbAttribute("freezer")
        var freezer: Boolean? = null,

        @get:DynamoDbAttribute("minibar")
        var minibar: Boolean? = null,

        @get:DynamoDbAttribute("basicKitchenItems")
        var basicKitchenItems: Boolean? = null,

        @get:DynamoDbAttribute("kitchenette")
        var kitchenette: Boolean? = null,

        @get:DynamoDbAttribute("dishwasher")
        var dishwasher: Boolean? = null,

        @get:DynamoDbAttribute("blender")
        var blender: Boolean? = null,

        @get:DynamoDbAttribute("trashCompactor")
        var trashCompactor: Boolean? = null,

        @get:DynamoDbAttribute("dishesAndCutlery")
        var dishesAndCutlery: Boolean? = null,

        @get:DynamoDbAttribute("diningTable")
        var diningTable: Boolean? = null,

        @get:DynamoDbAttribute("microwave")
        var microwave: Boolean? = null,

        @get:DynamoDbAttribute("breadMachine")
        var breadMachine: Boolean? = null,

        @get:DynamoDbAttribute("riceCooker")
        var riceCooker: Boolean? = null,

        @get:DynamoDbAttribute("refrigerator")
        var refrigerator: Boolean? = null,

        @get:DynamoDbAttribute("wineGlasses")
        var wineGlasses: Boolean? = null,

        @get:DynamoDbAttribute("toaster")
        var toaster: Boolean? = null,

        @get:DynamoDbAttribute("barbecueUtensils")
        var barbecueUtensils: Boolean? = null
)
