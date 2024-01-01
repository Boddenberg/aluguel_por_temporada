package juninwins.project.service.impl

import juninwins.project.enums.DiscountPolicyTypeEnum
import juninwins.project.exceptions.*
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.DiscountPolicy
import juninwins.project.model.guest.Guest
import juninwins.project.model.guest.GuestAccommodations
import juninwins.project.repository.AccommodationRepository
import juninwins.project.repository.DiscountPolicyRepository
import juninwins.project.repository.GuestAccommodationsRepository
import juninwins.project.service.AccommodationService
import juninwins.project.service.GuestService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccommocationServiceImpl(
    val accommodationRepository: AccommodationRepository,
    val discountPolicyRepository: DiscountPolicyRepository,
    val guestService: GuestService,
    val guestAccommodationsRepository: GuestAccommodationsRepository
) : AccommodationService {

    override fun save(accommodation: Accommodation, cpf: String): GuestAccommodations {

        val currentGuest = guestService.findGuestByCPF(cpf)

        checkGuestResponsibility(currentGuest)
        ensureDiscountPolicy(accommodation)
        ensureGuestIsHost(currentGuest)

        val guestAccommodations = getOrCreateGuestAccommodations(currentGuest)
        guestAccommodations.accommodations.add(accommodation)

        return guestAccommodationsRepository.save(guestAccommodations)
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

    override fun insertPolicyOnAccommodation(id: Long, newDiscountPolicy: DiscountPolicy): Accommodation {
        val accommodation = findById(id)

        val policyType = newDiscountPolicy.policyType
        val policyExists = accommodation._discountPolicy.any { it.policyType == policyType }
        if (policyExists) {
            throw DuplicatePolicyException(policyType)
        }

        val policyTypeEnum = DiscountPolicyTypeEnum.fromDescricao(policyType)

        if (policyTypeEnum == null) {
            throw PolicyTypeNotFoundException(policyType, DiscountPolicyTypeEnum.values())
        }

        val limitPolicy = 3
        if (accommodation._discountPolicy.size == limitPolicy) {
            throw PolicySizeThresholdException()
        }

        val updatedDiscountPolicy =
            accommodation._discountPolicy.filter { it.policyType != DiscountPolicyTypeEnum.NONE.toString() }

        accommodation._discountPolicy = updatedDiscountPolicy
        accommodation.addDiscountPolicy(newDiscountPolicy)
        return accommodationRepository.save(accommodation)
    }

    override fun updatedPolicyOnAccommodation(
        idAccommodation: Long,
        idPolicy: Long,
        discountPolicy: DiscountPolicy
    ): List<DiscountPolicy> {
        val accommodation = findById(idAccommodation)
        val accommodationPolicies = accommodation._discountPolicy
        for (policy in accommodationPolicies.reversed()) {
            if (policy.id == idPolicy) {
                policy.policyType = discountPolicy.policyType
                policy.discountPercentage - discountPolicy.discountPercentage
            }
        }
        accommodationPolicies.forEach(discountPolicyRepository::save)
        return accommodationPolicies
    }

    override fun deletePolicyById(id: Long): ResponseEntity<String> {
        val policyOptional = discountPolicyRepository.findById(id)
        if (policyOptional.isPresent) {
            discountPolicyRepository.deleteById(id)
            return ResponseEntity.ok("Policy $id excluded with success!")
        }
        throw PolicyIdNotFoundException(id)
    }

    override fun rateAccommodation(id: Long): ResponseEntity<String> {



        TODO("Not yet implemented")
    }

    private fun findById(id: Long): Accommodation {
        return accommodationRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }

    private fun checkGuestResponsibility(guest: Guest) {
        if (!guest.responsible) {
            throw GuestResponsibilityException()
        }
    }

    private fun ensureDiscountPolicy(accommodation: Accommodation) {
        if (accommodation._discountPolicy.isEmpty()) {
            accommodation.addDiscountPolicy(DiscountPolicy(DiscountPolicyTypeEnum.NONE.toString(), 0.00))
        }
    }

    private fun ensureGuestIsHost(guest: Guest) {
        if (!guest.host) {
            guest.host = true
        }
    }

    private fun getOrCreateGuestAccommodations(guest: Guest): GuestAccommodations {
        return guestAccommodationsRepository.findByGuest(guest)
                .orElse(GuestAccommodations(null, guest, mutableListOf()))
    }

}