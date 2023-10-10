package JuninWins.Project.service.impl

import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.DiscountPolicy
import JuninWins.Project.repository.AccommodationRepository
import JuninWins.Project.service.AccommodationService
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccomocationServiceImpl (val accomodationRepository: AccommodationRepository) : AccommodationService{

    private val modelMapper = ModelMapper()

    override fun save(accomocation: Accommodation): Accommodation {
        return accomodationRepository.save(accomocation)
    }

    override fun findAccomodationById(id: Long): Accommodation {
        return findById(id)
    }

    override fun update(id: Long, newAccomodation: Accommodation): Accommodation {
        val currentAccommodation = findById(id)
        if(currentAccommodation.discountPolicy == null && newAccomodation.discountPolicy != null) {
            currentAccommodation.discountPolicy = listOf(modelMapper.map(newAccomodation.discountPolicy, DiscountPolicy::class.java))
        }
        modelMapper.map(newAccomodation, currentAccommodation)

        return accomodationRepository.save(currentAccommodation)
    }

    override fun deleteById(id: Long): ResponseEntity<String> {
        val accommodation = accomodationRepository.findById(id)
        if (accommodation.isPresent) {
            accomodationRepository.deleteById(id)
            return ResponseEntity.status(200).body("Accommodation excluded with success!")
        }
        throw AccommodationIdNotFoundException(id)
    }


    private fun findById(id: Long): Accommodation {
        return accomodationRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }
}