package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Family(
        @get:DynamoDbAttribute("babyMonitor")
        var babyMonitor: Boolean? = null,

        @get:DynamoDbAttribute("babyBath")
        var babyBath: Boolean? = null,

        @get:DynamoDbAttribute("crib")
        var crib: Boolean? = null,

        @get:DynamoDbAttribute("childrensBicycles")
        var childrensBicycles: Boolean? = null,

        @get:DynamoDbAttribute("highChair")
        var highChair: Boolean? = null,

        @get:DynamoDbAttribute("playpenPortableCrib")
        var playpenPortableCrib: Boolean? = null,

        @get:DynamoDbAttribute("boardGames")
        var boardGames: Boolean? = null,

        @get:DynamoDbAttribute("childrensBooksAndToys")
        var childrensBooksAndToys: Boolean? = null,

        @get:DynamoDbAttribute("outdoorPlayground")
        var outdoorPlayground: Boolean? = null,

        @get:DynamoDbAttribute("babySafetyGates")
        var babySafetyGates: Boolean? = null,

        @get:DynamoDbAttribute("childrensDishesAndUtensils")
        var childrensDishesAndUtensils: Boolean? = null,

        @get:DynamoDbAttribute("tableCornerGuards")
        var tableCornerGuards: Boolean? = null,

        @get:DynamoDbAttribute("fireplaceGuards")
        var fireplaceGuards: Boolean? = null,

        @get:DynamoDbAttribute("outletCovers")
        var outletCovers: Boolean? = null,

        @get:DynamoDbAttribute("babysitterRecommendations")
        var babysitterRecommendations: Boolean? = null,

        @get:DynamoDbAttribute("windowGuardRails")
        var windowGuardRails: Boolean? = null,

        @get:DynamoDbAttribute("childrensPlayroom")
        var childrensPlayroom: Boolean? = null,

        @get:DynamoDbAttribute("changingTable")
        var changingTable: Boolean? = null
)
