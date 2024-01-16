package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Kitchen(
        var bakingSheet: Boolean?,
        var coffeeMaker: Boolean?,
        var coffee: Boolean?,
        var hotWaterKettle: Boolean?,
        var kitchen: Boolean?,
        var stove: Boolean?,
        var oven: Boolean?,
        var freezer: Boolean?,
        var minibar: Boolean?,
        var basicKitchenItems: Boolean?,
        var kitchenette: Boolean?,
        var dishwasher: Boolean?,
        var blender: Boolean?,
        var trashCompactor: Boolean?,
        var dishesAndCutlery: Boolean?,
        var diningTable: Boolean?,
        var microwave: Boolean?,
        var breadMachine: Boolean?,
        var riceCooker: Boolean?,
        var refrigerator: Boolean?,
        var wineGlasses: Boolean?,
        var toaster: Boolean?,
        var barbecueUtensils: Boolean?
)