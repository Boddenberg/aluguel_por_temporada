package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.exceptions.guest.GuestAlreadyRegisteredException
import juninwins.project.exceptions.guest.GuestNotFoundException
import juninwins.project.model.address.Address
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import juninwins.project.utils.validateAndFormatPhoneNumber
import org.springframework.stereotype.Service
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest

@Service
class GuestServiceImpl(
    private val dynamoDbClient: DynamoDbTemplate
) : GuestService {

    override fun saveGuest(customer: Guest): Guest {
        if (isCPFRegistered(customer.cpf)) {
            throw GuestAlreadyRegisteredException(customer.cpf)
        }
        customer.phoneNumber = validateAndFormatPhoneNumber(customer.phoneNumber)

        return dynamoDbClient.save(customer)
    }

    override fun findGuestByCPF(cpf: String): Guest {
        return findByCPF(cpf)
    }

    override fun findAllGuests(): List<Guest> {
        return dynamoDbClient.scanAll(Guest::class.java).items().stream().toList()
    }

    override fun updateGuest(guestDTO: UpdateGuestDTO): Guest {
        val existingGuest = findByCPF(guestDTO.cpf)

        guestDTO.phoneNumber?.let { validateAndFormatPhoneNumber(it) }

        val updatedGuest = mergeGuest(existingGuest, guestDTO)

        dynamoDbClient.update(updatedGuest)
        return updatedGuest
    }

    override fun deleteGuestByCPF(cpf: String) {
        if (!isCPFRegistered(cpf)) {
            throw GuestNotFoundException(cpf)
        }
        val key = Key.builder().partitionValue(cpf).build()

        dynamoDbClient.delete(key, Guest::class.java)
    }

    private fun findByCPF(cpf: String): Guest {
        if (!isCPFRegistered(cpf)) {
            throw GuestNotFoundException(cpf)
        }

        val queryEnhancedRequest = QueryEnhancedRequest.builder().queryConditional(
            QueryConditional.keyEqualTo(
                Key.builder().partitionValue(cpf).build()
            )
        ).build()

        return dynamoDbClient.query(queryEnhancedRequest, Guest::class.java).items().stream().findFirst().get()
    }

    private fun isCPFRegistered(cpf: String): Boolean {
        val queryEnhancedRequest = QueryEnhancedRequest.builder().queryConditional(
            QueryConditional.keyEqualTo(
                Key.builder().partitionValue(cpf).build()
            )
        ).build()

        val response = dynamoDbClient.query(queryEnhancedRequest, Guest::class.java)
        return response.items().stream().findFirst().isPresent
    }

    fun mergeGuest(existingGuest: Guest, guestDTO: UpdateGuestDTO): Guest {
        return existingGuest.copy(
            name = guestDTO.name ?: existingGuest.name,
            lastName = guestDTO.lastName ?: existingGuest.lastName,
            email = guestDTO.email ?: existingGuest.email,
            phoneNumber = guestDTO.phoneNumber ?: existingGuest.phoneNumber,
            birthDate = guestDTO.birthDate ?: existingGuest.birthDate,
            responsible = guestDTO.responsible ?: existingGuest.responsible,
            host = guestDTO.host ?: existingGuest.host,
            address = mergeAddress(existingGuest.address, guestDTO.address)
        )
    }

    fun mergeAddress(existingAddress: Address?, newAddress: Address?): Address? {
        if (existingAddress == null) return newAddress
        if (newAddress == null) return existingAddress

        return existingAddress.copy(
            logradouro = newAddress.logradouro ?: existingAddress.logradouro,
            numero = newAddress.numero ?: existingAddress.numero,
            complemento = newAddress.complemento ?: existingAddress.complemento,
            bairro = newAddress.bairro ?: existingAddress.bairro,
            localidade = newAddress.localidade ?: existingAddress.localidade,
            uf = newAddress.uf ?: existingAddress.uf,
            cep = newAddress.cep ?: existingAddress.cep
        )
    }
}
