package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Family(
        var babyMonitor: Boolean?,
        var babyBath: Boolean?,
        var crib: Boolean?,
        var childrensBicycles: Boolean?,
        var highChair: Boolean?,
        var playpenPortableCrib: Boolean?,
        var boardGames: Boolean?,
        var childrensBooksAndToys: Boolean?,
        var outdoorPlayground: Boolean?,
        var babySafetyGates: Boolean?,
        var childrensDishesAndUtensils: Boolean?,
        var tableCornerGuards: Boolean?,
        var fireplaceGuards: Boolean?,
        var outletCovers: Boolean?,
        var babysitterRecommendations: Boolean?,
        var windowGuardRails: Boolean?,
        var childrensPlayroom: Boolean?,
        var changingTable: Boolean?
)