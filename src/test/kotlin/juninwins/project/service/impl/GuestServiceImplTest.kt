package juninwins.project.service.impl

import juninwins.project.exceptions.guest.GuestAlreadyRegisteredException
import juninwins.project.exceptions.guest.GuestNotFoundException
import juninwins.project.model.address.Address
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.model.guest.Guest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

class GuestServiceImplTest {

    private val dynamoDbClient: DynamoDbClient = mock(DynamoDbClient::class.java)
    private val guestService = GuestServiceImpl(dynamoDbClient)

    @Test
    fun `should save a guest successfully`() {
        val guest = Guest(cpf = "11122233344", name = "Beyoncé", phoneNumber = "987654321")

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().build())

        `when`(dynamoDbClient.putItem(any(PutItemRequest::class.java)))
            .thenReturn(PutItemResponse.builder().build())

        val result = guestService.saveGuest(guest)

        assertEquals(guest, result)
        verify(dynamoDbClient, times(1)).putItem(any(PutItemRequest::class.java))
    }

    @Test
    fun `should throw exception when saving a guest with already registered CPF`() {
        val guest = Guest(cpf = "11122233344", name = "Rihanna", phoneNumber = "123456789")

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().item(mapOf("cpf" to AttributeValue.builder().s(guest.cpf).build())).build())

        assertThrows<GuestAlreadyRegisteredException> {
            guestService.saveGuest(guest)
        }

        verify(dynamoDbClient, never()).putItem(any(PutItemRequest::class.java))
    }

    @Test
    fun `should find a guest by CPF`() {
        val guest = Guest(cpf = "22233344455", name = "Lady Gaga", phoneNumber = "789456123")

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().item(mapOf("cpf" to AttributeValue.builder().s(guest.cpf).build())).build())

        val result = guestService.findGuestByCPF(guest.cpf)

        assertEquals(guest.cpf, result.cpf)
        verify(dynamoDbClient, times(1)).getItem(any(GetItemRequest::class.java))
    }

    @Test
    fun `should throw exception when guest not found by CPF`() {
        val cpf = "33344455566"

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().build())

        assertThrows<GuestNotFoundException> {
            guestService.findGuestByCPF(cpf)
        }

        verify(dynamoDbClient, times(1)).getItem(any(GetItemRequest::class.java))
    }

    @Test
    fun `should delete a guest by CPF`() {
        val cpf = "44455566677"

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().item(mapOf("cpf" to AttributeValue.builder().s(cpf).build())).build())

        `when`(dynamoDbClient.deleteItem(any(DeleteItemRequest::class.java)))
            .thenReturn(DeleteItemResponse.builder().build())

        guestService.deleteGuestByCPF(cpf)

        verify(dynamoDbClient, times(1)).deleteItem(any(DeleteItemRequest::class.java))
    }

    @Test
    fun `should throw exception when deleting a guest that does not exist`() {
        val cpf = "55566677788"

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().build())

        assertThrows<GuestNotFoundException> {
            guestService.deleteGuestByCPF(cpf)
        }

        verify(dynamoDbClient, never()).deleteItem(any(DeleteItemRequest::class.java))
    }

    @Test
    fun `should merge guest correctly`() {
        val existingGuest = Guest(cpf = "66677788899", name = "Adele", phoneNumber = "123123123")
        val guestDTO = UpdateGuestDTO(cpf = "66677788899", name = "Adele Laurie", phoneNumber = "321321321")

        val mergedGuest = guestService.mergeGuest(existingGuest, guestDTO)

        assertEquals("Adele Laurie", mergedGuest.name)
        assertEquals("321321321", mergedGuest.phoneNumber)
        assertEquals(existingGuest.cpf, mergedGuest.cpf)
    }

    @Test
    fun `should merge address correctly`() {
        val existingAddress = Address(logradouro = "Street A", numero = "123")
        val newAddress = Address(logradouro = "Street B")

        val mergedAddress = guestService.mergeAddress(existingAddress, newAddress)

        assertEquals("Street B", mergedAddress?.logradouro)
        assertEquals("123", mergedAddress?.numero)
    }

    @Test
    fun `should find all guests`() {
        val items = listOf(
            mapOf("cpf" to AttributeValue.builder().s("77788899900").build()),
            mapOf("cpf" to AttributeValue.builder().s("88899900011").build())
        )

        `when`(dynamoDbClient.scan(any(ScanRequest::class.java)))
            .thenReturn(ScanResponse.builder().items(items).build())

        val guests = guestService.findAllGuests()

        assertEquals(2, guests.size)
        verify(dynamoDbClient, times(1)).scan(any(ScanRequest::class.java))
    }

    @Test
    fun `should update guest`() {
        val existingGuest = Guest(cpf = "99900011122", name = "Taylor Swift", phoneNumber = "456456456")
        val guestDTO = UpdateGuestDTO(cpf = "99900011122", name = "Taylor Alison Swift", phoneNumber = "654654654")

        `when`(dynamoDbClient.getItem(any(GetItemRequest::class.java)))
            .thenReturn(GetItemResponse.builder().item(mapOf("cpf" to AttributeValue.builder().s(existingGuest.cpf).build())).build())

        `when`(dynamoDbClient.putItem(any(PutItemRequest::class.java)))
            .thenReturn(PutItemResponse.builder().build())

        val updatedGuest = guestService.updateGuest(guestDTO)

        assertEquals("Taylor Alison Swift", updatedGuest.name)
        assertEquals("654654654", updatedGuest.phoneNumber)
        verify(dynamoDbClient, times(1)).putItem(any(PutItemRequest::class.java))
    }
}
