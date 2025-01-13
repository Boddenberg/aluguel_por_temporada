package juninwins.project.service.impl

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.exceptions.accommodation.AccommodationAlreadyRegisteredException
import juninwins.project.exceptions.accommodation.AccommodationIdNotFoundException
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.DTO.AccommodationDTO
import juninwins.project.service.AccommodationService
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest

@Service
class AccommodationServiceImpl(
    private val dynamoDbClient: DynamoDbTemplate
) : AccommodationService {

    override fun saveAccommodation(accommodation: Accommodation): Accommodation {
        if (isIdRegistered(accommodation.id)) {
            throw AccommodationAlreadyRegisteredException(accommodation.id)
        }

        dynamoDbClient.save(accommodation)
        return accommodation
    }

    override fun findAccommodationById(id: String): Accommodation {
        return findById(id)
    }

    override fun findAccommodationByCPF(cpf: String): List<Accommodation> {
        val queryEnhancedRequest = QueryEnhancedRequest.builder().queryConditional(
            QueryConditional.keyEqualTo(
                Key.builder().partitionValue(cpf).build()
            )
        ).build()

        return dynamoDbClient.query(queryEnhancedRequest, Accommodation::class.java).items().stream().toList()
    }

    override fun findAllAccommodations(): List<Accommodation> {
        return dynamoDbClient.scanAll(Accommodation::class.java).items().stream().toList()
    }

    override fun updateAccommodation(accommodationDTO: AccommodationDTO): Accommodation {
        val existingAccommodation = findById(accommodationDTO.id)

        val updatedAccommodation = mergeAccommodation(existingAccommodation, accommodationDTO)

        dynamoDbClient.update(updatedAccommodation)
        return updatedAccommodation
    }

    override fun deleteAccommodationById(id: String) {
        if (!isIdRegistered(id)) {
            throw AccommodationIdNotFoundException(id)
        }

        val deleteKey = mapOf("id" to AttributeValue.builder().s(id).build())
        val deleteItemRequest = DeleteItemRequest.builder()
            .tableName(Accommodation::class.simpleName)
            .key(deleteKey)
            .build()

        dynamoDbClient.delete(deleteItemRequest)
    }

    private fun findById(id: String): Accommodation {
        if (!isIdRegistered(id)) {
            throw AccommodationIdNotFoundException(id)
        }

        val queryEnhancedRequest = QueryEnhancedRequest.builder().queryConditional(
            QueryConditional.keyEqualTo(
                Key.builder().partitionValue(id).build()
            )
        ).build()

        return dynamoDbClient.query(queryEnhancedRequest, Accommodation::class.java).items().stream().findFirst().get()
    }

    private fun isIdRegistered(id: String): Boolean {
        val queryEnhancedRequest = QueryEnhancedRequest.builder().queryConditional(
            QueryConditional.keyEqualTo(
                Key.builder().partitionValue(id).build()
            )
        ).build()

        val response = dynamoDbClient.query(queryEnhancedRequest, Accommodation::class.java)
        return response.items().stream().findFirst().isPresent
    }

    private fun mergeAccommodation(existingAccommodation: Accommodation, accommodationDTO: AccommodationDTO): Accommodation {
        return existingAccommodation.copy(
            type = accommodationDTO.type,
            capacity = accommodationDTO.capacity,
            basePrice = accommodationDTO.basePrice ?: existingAccommodation.basePrice,
            amenities = accommodationDTO.amenities ?: existingAccommodation.amenities,
            reviews = accommodationDTO.reviews ?: existingAccommodation.reviews,
            address = accommodationDTO.address ?: existingAccommodation.address
        )
    }
}
