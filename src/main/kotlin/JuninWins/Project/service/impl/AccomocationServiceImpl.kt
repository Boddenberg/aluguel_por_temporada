package JuninWins.Project.service.impl

import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Accommodation
import JuninWins.Project.repository.AccommodationRepository
import JuninWins.Project.service.AccommodationService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccomocationServiceImpl (val accomodationRepository: AccommodationRepository) : AccommodationService{

    private val modelMapper = ModelMapper()

    override fun save(accomocation: Accommodation): Accommodation {
        return accomodationRepository.save(accomocation)
    }

    override fun findAccomodationById(id: String): Accommodation {
        return findById(id)
    }

    override fun update(id: String, newAccomodation: Accommodation): Accommodation {
        val currentAccommodation = findById(id)

        modelMapper.map(newAccomodation, currentAccommodation)

        return accomodationRepository.save(currentAccommodation)
    }

    override fun deleteById(id: String): ResponseEntity<String> {
        val accommodation = accomodationRepository.findById(id)

        if (accommodation.isPresent) {
            accomodationRepository.deleteById(id)
            return ResponseEntity.ok("Accommodation excluded with success!")
        } //TODO: Validação para excluir acomodação apenas se não tiver reservas atreladas a ela.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Accomodation not found")
    }


    private fun findById(id: String): Accommodation {
        return accomodationRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }
}