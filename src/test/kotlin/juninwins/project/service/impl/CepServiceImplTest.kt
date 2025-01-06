package juninwins.project.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class CepServiceImplTest {

    private val restTemplate: RestTemplate = Mockito.mock(RestTemplate::class.java)
    private val cepService = CepServiceImpl()

    @Test
    fun `should return address when valid CEP is provided`() {
        val cep = "07190-220"
        val mockResponse = mapOf(
            "logradouro" to "Rua Vitória da Conquista",
            "bairro" to "Seringueiras",
            "localidade" to "Guarulhos",
            "uf" to "SP",
            "cep" to "07190-220"
        )

        Mockito.`when`(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(Map::class.java)))
            .thenReturn(ResponseEntity.ok(mockResponse))

        val address = cepService.getAddressByCep(cep)

        assertEquals("Rua Vitória da Conquista", address.logradouro)
        assertEquals("SP", address.uf)
        assertEquals("07190-220", address.cep)
    }

    @Test
    fun `should throw exception when CEP is not found`() {
        val cep = "00000000"
        val mockResponse = mapOf("erro" to true)

        Mockito.`when`(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(Map::class.java)))
            .thenReturn(ResponseEntity.ok(mockResponse))

        val exception = assertThrows<RuntimeException> {
            cepService.getAddressByCep(cep)
        }
        assertEquals("CEP não encontrado.", exception.message)
    }

}
