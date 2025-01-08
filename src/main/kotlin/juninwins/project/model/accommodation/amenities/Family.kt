package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute


@JsonInclude(JsonInclude.Include.NON_NULL)
data class Family(
        @get:DynamoDbAttribute("babyMonitor")
        var babyMonitor: Boolean?,

        @get:DynamoDbAttribute("babyBath")
        var babyBath: Boolean?,

        @get:DynamoDbAttribute("crib")
        var crib: Boolean?,

        @get:DynamoDbAttribute("childrensBicycles")
        var childrensBicycles: Boolean?,

        @get:DynamoDbAttribute("highChair")
        var highChair: Boolean?,

        @get:DynamoDbAttribute("playpenPortableCrib")
        var playpenPortableCrib: Boolean?,

        @get:DynamoDbAttribute("boardGames")
        var boardGames: Boolean?,

        @get:DynamoDbAttribute("childrensBooksAndToys")
        var childrensBooksAndToys: Boolean?,

        @get:DynamoDbAttribute("outdoorPlayground")
        var outdoorPlayground: Boolean?,

        @get:DynamoDbAttribute("babySafetyGates")
        var babySafetyGates: Boolean?,

        @get:DynamoDbAttribute("childrensDishesAndUtensils")
        var childrensDishesAndUtensils: Boolean?,

        @get:DynamoDbAttribute("tableCornerGuards")
        var tableCornerGuards: Boolean?,

        @get:DynamoDbAttribute("fireplaceGuards")
        var fireplaceGuards: Boolean?,

        @get:DynamoDbAttribute("outletCovers")
        var outletCovers: Boolean?,

        @get:DynamoDbAttribute("babysitterRecommendations")
        var babysitterRecommendations: Boolean?,

        @get:DynamoDbAttribute("windowGuardRails")
        var windowGuardRails: Boolean?,

        @get:DynamoDbAttribute("childrensPlayroom")
        var childrensPlayroom: Boolean?,

        @get:DynamoDbAttribute("changingTable")
        var changingTable: Boolean?
)
