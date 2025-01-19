package juninwins.project.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import juninwins.project.exceptions.guest.GuestAlreadyRegisteredException
import juninwins.project.exceptions.guest.GuestNotFoundException
import juninwins.project.model.address.Address
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.model.guest.Guest
import juninwins.project.service.GuestService
import juninwins.project.utils.validateAndFormatPhoneNumber
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

@Service
class GuestServiceImpl(
    private val dynamoDbClient: DynamoDbClient
) : GuestService {

    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun saveGuest(customer: Guest): Guest {
        if (isCPFRegistered(customer.cpf)) {
            throw GuestAlreadyRegisteredException(customer.cpf)
        }

        customer.phoneNumber = validateAndFormatPhoneNumber(customer.phoneNumber)

        dynamoDbClient.putItem(createPutItemRequest(customer))
        return customer
    }

    override fun findGuestByCPF(cpf: String): Guest {
        return findByCPF(cpf) ?: throw GuestNotFoundException(cpf)
    }

    override fun findAllGuests(): List<Guest> {
        val scanRequest = ScanRequest.builder()
            .tableName(Guest::class.simpleName)
            .build()

        val scanResponse = dynamoDbClient.scan(scanRequest)
        return scanResponse.items().map { item ->
            mapper.convertValue(item.mapValues { it.value.toAttributeValue() }, Guest::class.java)
        }
    }

    override fun updateGuest(guestDTO: UpdateGuestDTO): Guest {
        val existingGuest = findByCPF(guestDTO.cpf) ?: throw GuestNotFoundException(guestDTO.cpf)
        if (!guestDTO.phoneNumber.isNullOrEmpty()) {
            validateAndFormatPhoneNumber(guestDTO.phoneNumber)
        }
        val updatedGuest = mergeGuestDTO(existingGuest, guestDTO)

        dynamoDbClient.putItem(createPutItemRequest(updatedGuest))
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

        dynamoDbClient.deleteItem(deleteItemRequest)
    }

    private fun findByCPF(cpf: String): Guest? {
        val getKey = mapOf("cpf" to AttributeValue.builder().s(cpf).build())
        val getItemRequest = GetItemRequest.builder().tableName(Guest::class.simpleName).key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)

        return if (response.hasItem()) {
            mapper.convertValue(response.item().mapValues { it.value.toAttributeValue() }, Guest::class.java)
        } else null
    }

    private fun isCPFRegistered(cpf: String): Boolean {
        val getKey = mapOf("cpf" to AttributeValue.builder().s(cpf).build())
        val getItemRequest = GetItemRequest.builder().tableName(Guest::class.simpleName).key(getKey).build()
        val response = dynamoDbClient.getItem(getItemRequest)
        return response.hasItem()
    }

    private fun mergeGuestDTO(existingGuest: Guest, newGuest: UpdateGuestDTO): Guest {
        return existingGuest.copy(
            name = newGuest.name?.takeIf { it.isNotBlank() } ?: existingGuest.name,
            lastName = newGuest.lastName?.takeIf { it.isNotBlank() } ?: existingGuest.lastName,
            email = newGuest.email?.takeIf { it.isNotBlank() } ?: existingGuest.email,
            phoneNumber = newGuest.phoneNumber?.takeIf { it.isNotBlank() } ?: existingGuest.phoneNumber,
            birthDate = newGuest.birthDate?.takeIf { it.isNotBlank() } ?: existingGuest.birthDate,
            responsible = newGuest.responsible ?: existingGuest.responsible,
            host = newGuest.host ?: existingGuest.host,
            address = newGuest.address ?: existingGuest.address
        )
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
