package juninwins.project.controller

import juninwins.project.mocks.guest.GuestMocks
import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.service.GuestService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
class GuestControllerTest {

    private val guestService: GuestService = Mockito.mock(GuestService::class.java)
    private val guestController = GuestController(guestService)
    private val cpf = "46290103865"
    private val guest = GuestMocks.guestMock()
    private val guestList = GuestMocks.guestList()

    @Test
    fun `should find a guest by CPF`() {
        Mockito.`when`(guestService.findGuestByCPF(cpf)).thenReturn(guest)

        val response = guestController.findGuest(cpf)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(guest, response.body)
    }

    @Test
    fun `should return all guests`() {
        Mockito.`when`(guestService.findAllGuests()).thenReturn(guestList)

        val response = guestController.findAllGuests()

        assertEquals(guestList, response)
    }

    @Test
    fun `should register a guest`() {
        Mockito.`when`(guestService.saveGuest(guest)).thenReturn(guest)

        val response = guestController.saveGuest(guest)

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun `should register a guest and return it`() {
        Mockito.`when`(guestService.saveGuest(guest)).thenReturn(guest)

        val response = guestController.saveGuests(guest)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(guest, response.body)
    }

    @Test
    fun `should update a guest`() {
        val updateGuestDTO = UpdateGuestDTO(
            cpf = cpf,
            name = "Taylor Swift",
            email = "taylor@swift.com"
        )

        val updatedGuest = guest.copy(
            name = "Shakira Waka Waka",
            email = "shakira@tzamina.eheh"
        )

        Mockito.`when`(guestService.updateGuest(updateGuestDTO)).thenReturn(updatedGuest)

        val response = guestController.updateGuest(updateGuestDTO)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(updatedGuest, response.body)
    }

    @Test
    fun `should delete a guest by CPF`() {
        Mockito.doNothing().`when`(guestService).deleteGuestByCPF(cpf)

        val response = guestController.deleteGuest(cpf)

        assertEquals(HttpStatus.OK, response.statusCode)
    }
}

