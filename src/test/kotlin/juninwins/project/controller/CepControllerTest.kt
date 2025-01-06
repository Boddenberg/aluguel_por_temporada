package juninwins.project.controller

import juninwins.project.mocks.address.AddressMocks.addressMock
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
    fun `should return 200 and address when valid CEP is provided`() {
        val cep = "07190-220"

        `when`(cepService.getAddressByCep(cep)).thenReturn(address)

        val response: ResponseEntity<Any> = cepController.getAddressByCep(cep)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(address, response.body)
        verify(cepService, times(1)).getAddressByCep(cep)
    }

    @Test
    fun `should return 204 when CEP is not found`() {
        val cep = "00000000"

        `when`(cepService.getAddressByCep(cep)).thenReturn(null)

        val response: ResponseEntity<Any> = cepController.getAddressByCep(cep)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertEquals(null, response.body)
        verify(cepService, times(1)).getAddressByCep(cep)
    }
}
