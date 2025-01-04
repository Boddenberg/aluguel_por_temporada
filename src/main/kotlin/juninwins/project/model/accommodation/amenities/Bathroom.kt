package juninwins.project.model.accommodation.amenities

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Bathroom (
        val bathtub: Boolean?,
        val bidet: Boolean?,
        val outdoorShower: Boolean?,
        val conditioner: Boolean?,
        val showerGel: Boolean?,
        val cleaningProducts: Boolean?,
        val bodySoap: Boolean?,
        val hairDryer: Boolean?,
        val shampoo: Boolean?,
        val hotWater: Boolean?
)
