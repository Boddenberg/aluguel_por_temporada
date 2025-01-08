package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.awspring.cloud.dynamodb.DynamoDbTemplate
import juninwins.project.exceptions.guest.GuestAlreadyRegisteredException
import juninwins.project.exceptions.guest.GuestNotFoundException
import juninwins.project.model.address.Address
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

    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun save(customer: Guest): Guest {
        if (isCPFRegistered(customer.cpf)) {
            throw GuestAlreadyRegisteredException(customer.cpf)
        }

        customer.phoneNumber = validateAndFormatPhoneNumber(customer.phoneNumber)

        dynamoDbClient.save(customer)
        return customer
    }

    override fun findGuestByCPF(cpf: String): Guest {
        return findByCPF(cpf) ?: throw GuestNotFoundException(cpf)
    }

    override fun findAllGuests(): List<Guest> {
        return dynamoDbClient.scanAll(Guest::class.java).items().stream().toList()
    }

    override fun updateGuest(guest: Guest): Guest {
        val existingGuest = findByCPF(guest.cpf) ?: throw GuestNotFoundException(guest.cpf)
        validateAndFormatPhoneNumber(guest.phoneNumber)

        val updatedGuest = mergeGuest(existingGuest, guest)

        dynamoDbClient.update(updatedGuest)
        return updatedGuest
    }

    override fun deleteGuestByCPF(cpf: String) {
        if (!isCPFRegistered(cpf)) {
            throw GuestNotFoundException(cpf)
        }

        val deleteKey = mapOf("cpf" to AttributeValue.builder().s(cpf).build())
        val deleteItemRequest = DeleteItemRequest.builder()
            .tableName(Guest::class.simpleName)
            .key(deleteKey)
            .build()

        dynamoDbClient.delete(deleteItemRequest)
    }

    private fun findByCPF(cpf: String): Guest? {
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

    private fun mergeGuest(existingGuest: Guest, newGuest: Guest): Guest {
        return existingGuest.copy(
            name = newGuest.name.takeIf { it.isNotBlank() } ?: existingGuest.name,
            lastName = newGuest.lastName.takeIf { it.isNotBlank() } ?: existingGuest.lastName,
            email = newGuest.email.takeIf { it.isNotBlank() } ?: existingGuest.email,
            phoneNumber = newGuest.phoneNumber.takeIf { it.isNotBlank() } ?: existingGuest.phoneNumber,
            birthDate = newGuest.birthDate.takeIf { it.isNotBlank() } ?: existingGuest.birthDate,
            responsible = newGuest.responsible ?: existingGuest.responsible,
            host = newGuest.host ?: existingGuest.host,
            address = mergeAddress(existingGuest.address, newGuest.address)
        )
    }

    private fun mergeAddress(existingAddress: Address?, newAddress: Address?): Address? {
        if (existingAddress == null) return newAddress
        if (newAddress == null) return existingAddress

        return existingAddress.copy(
            logradouro = newAddress.logradouro?.takeIf { it.isNotBlank() } ?: existingAddress.logradouro,
            numero = newAddress.numero?.takeIf { it.isNotBlank() } ?: existingAddress.numero,
            complemento = newAddress.complemento?.takeIf { it.isNotBlank() } ?: existingAddress.complemento,
            bairro = newAddress.bairro?.takeIf { it.isNotBlank() } ?: existingAddress.bairro,
            localidade = newAddress.localidade?.takeIf { it.isNotBlank() } ?: existingAddress.localidade,
            uf = newAddress.uf?.takeIf { it.isNotBlank() } ?: existingAddress.uf,
            cep = newAddress.cep?.takeIf { it.isNotBlank() } ?: existingAddress.cep
        )
    }

    private fun createPutItemRequest(guest: Guest): PutItemRequest {
        val itemMap = mapper.convertValue(guest, Map::class.java) as Map<String, Any>
        val attributeValueMap = itemMap.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }

        return PutItemRequest.builder()
            .tableName(Guest::class.simpleName)
            .item(attributeValueMap)
            .build()
    }

    private fun Map<String, *>.toAttributeValueMap(): Map<String, AttributeValue> {
        return this.mapValues { entry ->
            when (entry.value) {
                is Map<*, *> -> AttributeValue.builder().m((entry.value as Map<String, *>).toAttributeValueMap()).build()
                is Boolean -> AttributeValue.builder().bool(entry.value as Boolean).build()
                else -> AttributeValue.builder().s(entry.value.toString()).build()
            }
        }
    }

    private fun AttributeValue.toAttributeValue(): Any {
        return when {
            this.s() != null -> this.s()
            this.bool() != null -> this.bool()
            this.m() != null -> this.m().mapValues { it.value.toAttributeValue() }
            else -> throw IllegalArgumentException("Tipo de atributo não suportado: $this")
        }
    }
}
