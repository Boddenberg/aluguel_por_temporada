package juninwins.project.controller

import juninwins.project.mocks.address.AddressMocks.addressMock
import juninwins.project.model.address.Address
import juninwins.project.service.CepService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CepControllerTest {

    private val cepService: CepService = mock(CepService::class.java)
    private val cepController = CepController(cepService)
    private val address = addressMock()

    @Test
    fun `should return address for valid CEP`() {
        val cep = "07190220"

        `when`(cepService.getAddressByCep(cep)).thenReturn(address)

        val response: ResponseEntity<Address> = cepController.getAddressByCep(cep)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(address, response.body)
        verify(cepService, times(1)).getAddressByCep(cep)
    }
}
