package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Entertainment(
        @get:DynamoDbAttribute("cinema")
        var cinema: Boolean? = null,

        @get:DynamoDbAttribute("ethernetConnection")
        var ethernetConnection: Boolean? = null,

        @get:DynamoDbAttribute("videoGameConsole")
        var videoGameConsole: Boolean? = null,

        @get:DynamoDbAttribute("gymEquipment")
        var gymEquipment: Boolean? = null,

        @get:DynamoDbAttribute("arcade")
        var arcade: Boolean? = null,

        @get:DynamoDbAttribute("baseballTrainingCage")
        var baseballTrainingCage: Boolean? = null,

        @get:DynamoDbAttribute("lifeSizeGames")
        var lifeSizeGames: Boolean? = null,

        @get:DynamoDbAttribute("laserTag")
        var laserTag: Boolean? = null,

        @get:DynamoDbAttribute("booksAndReadingMaterial")
        var booksAndReadingMaterial: Boolean? = null,

        @get:DynamoDbAttribute("poolTable")
        var poolTable: Boolean? = null,

        @get:DynamoDbAttribute("pingPongTable")
        var pingPongTable: Boolean? = null,

        @get:DynamoDbAttribute("miniGolf")
        var miniGolf: Boolean? = null,

        @get:DynamoDbAttribute("climbingWall")
        var climbingWall: Boolean? = null,

        @get:DynamoDbAttribute("piano")
        var piano: Boolean? = null,

        @get:DynamoDbAttribute("bowlingAlley")
        var bowlingAlley: Boolean? = null,

        @get:DynamoDbAttribute("skateRamp")
        var skateRamp: Boolean? = null,

        @get:DynamoDbAttribute("themedRoom")
        var themedRoom: Boolean? = null,

        @get:DynamoDbAttribute("soundSystem")
        var soundSystem: Boolean? = null,

        @get:DynamoDbAttribute("turntable")
        var turntable: Boolean? = null,

        @get:DynamoDbAttribute("tv")
        var tv: Boolean? = null
)
