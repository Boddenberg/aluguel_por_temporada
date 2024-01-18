package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Embeddable

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Streaming(
        var primeVideo: Boolean?,
        var netflix: Boolean?,
        var hbo: Boolean?,
        var disneyPlus: Boolean?,
        var globoplay: Boolean?,
        var appleTvPlus: Boolean?,
        var paramountPlus: Boolean?,
        var starPlus: Boolean?,
        var crunchyroll: Boolean?
)