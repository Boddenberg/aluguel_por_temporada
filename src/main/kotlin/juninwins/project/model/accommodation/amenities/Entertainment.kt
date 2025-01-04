package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Entertainment(
        var cinema: Boolean?,
        var ethernetConnection: Boolean?,
        var videoGameConsole: Boolean?,
        var gymEquipment: Boolean?,
        var arcade: Boolean?,
        var baseballTrainingCage: Boolean?,
        var lifeSizeGames: Boolean?,
        var laserTag: Boolean?,
        var booksAndReadingMaterial: Boolean?,
        var poolTable: Boolean?,
        var pingPongTable: Boolean?,
        var miniGolf: Boolean?,
        var climbingWall: Boolean?,
        var piano: Boolean?,
        var bowlingAlley: Boolean?,
        var skateRamp: Boolean?,
        var themedRoom: Boolean?,
        var soundSystem: Boolean?,
        var turntable: Boolean?,
        var tv: Boolean?
)