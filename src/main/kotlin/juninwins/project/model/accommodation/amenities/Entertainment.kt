package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Entertainment(
        @get:DynamoDbAttribute("cinema")
        var cinema: Boolean?,

        @get:DynamoDbAttribute("ethernetConnection")
        var ethernetConnection: Boolean?,

        @get:DynamoDbAttribute("videoGameConsole")
        var videoGameConsole: Boolean?,

        @get:DynamoDbAttribute("gymEquipment")
        var gymEquipment: Boolean?,

        @get:DynamoDbAttribute("arcade")
        var arcade: Boolean?,

        @get:DynamoDbAttribute("baseballTrainingCage")
        var baseballTrainingCage: Boolean?,

        @get:DynamoDbAttribute("lifeSizeGames")
        var lifeSizeGames: Boolean?,

        @get:DynamoDbAttribute("laserTag")
        var laserTag: Boolean?,

        @get:DynamoDbAttribute("booksAndReadingMaterial")
        var booksAndReadingMaterial: Boolean?,

        @get:DynamoDbAttribute("poolTable")
        var poolTable: Boolean?,

        @get:DynamoDbAttribute("pingPongTable")
        var pingPongTable: Boolean?,

        @get:DynamoDbAttribute("miniGolf")
        var miniGolf: Boolean?,

        @get:DynamoDbAttribute("climbingWall")
        var climbingWall: Boolean?,

        @get:DynamoDbAttribute("piano")
        var piano: Boolean?,

        @get:DynamoDbAttribute("bowlingAlley")
        var bowlingAlley: Boolean?,

        @get:DynamoDbAttribute("skateRamp")
        var skateRamp: Boolean?,

        @get:DynamoDbAttribute("themedRoom")
        var themedRoom: Boolean?,

        @get:DynamoDbAttribute("soundSystem")
        var soundSystem: Boolean?,

        @get:DynamoDbAttribute("turntable")
        var turntable: Boolean?,

        @get:DynamoDbAttribute("tv")
        var tv: Boolean?
)
