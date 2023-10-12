package JuninWins.Project.service.impl

import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.DiscountPolicy
import JuninWins.Project.repository.AccommodationRepository
import JuninWins.Project.repository.DiscountPolicyRepository
import JuninWins.Project.service.AccommodationService
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccommocationServiceImpl (val accommodationRepository: AccommodationRepository,
                                val discountPolicyRepository: DiscountPolicyRepository) : AccommodationService{

    private val modelMapper = ModelMapper()

    override fun save(accomocation: Accommodation): Accommodation {
        return accommodationRepository.save(accomocation)
    }

    override fun findAccomodationById(id: Long): Accommodation {
        return findById(id)
    }

    override fun update(id: Long, newAccomodation: Accommodation): Accommodation {
        val currentAccommodation = findById(id)

        currentAccommodation.type = newAccomodation.type
        currentAccommodation.localization = newAccomodation.localization
        currentAccommodation.capacity = newAccomodation.capacity
        currentAccommodation.basePrice = newAccomodation.basePrice
        currentAccommodation.address = newAccomodation.address

        return accommodationRepository.save(currentAccommodation)
    }

    override fun deleteById(id: Long): ResponseEntity<String> {
        val accommodation = accommodationRepository.findById(id)
        if (accommodation.isPresent) {
            accommodationRepository.deleteById(id)
            return ResponseEntity.status(200).body("Accommodation excluded with success!")
        }
        throw AccommodationIdNotFoundException(id)
    }

    override fun insertPolicyOnAccommodation(id: Long, discountPolicy: DiscountPolicy): Accommodation {
        var accommodation = findById(id)
        accommodation.addDiscountPolicy(discountPolicy)
        return accommodationRepository.save(accommodation)
    }

    override fun updatedPolicyOnAccommodation(
        idAccommodation: Long,
        idPolicy: Long,
        discountPolicy: DiscountPolicy
    ): List<DiscountPolicy> {
        val accommodation = findById(idAccommodation)
        for (policy in accommodation._discountPolicy.reversed()) {
            if (policy.id == idPolicy) {
                policy.policyType = discountPolicy.policyType
                policy.discountPercentage - discountPolicy.discountPercentage
            }
        }
        accommodation._discountPolicy.forEach(discountPolicyRepository::save)
        return accommodation._discountPolicy
    }

    private fun findById(id: Long): Accommodation {
        return accommodationRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }
}