package juninwins.project.service.impl

import juninwins.project.model.address.Address
import juninwins.project.service.CepService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException

@Service
class CepServiceImpl : CepService {

    private val restTemplate = RestTemplate()

    override fun getAddressByCep(cep: String): Address {
        val url = "https://viacep.com.br/ws/$cep/json/"
        return try {
            val response = restTemplate.getForEntity(url, Map::class.java)
            if (response.statusCode.is2xxSuccessful && response.body != null) {
                mapViaCepResponseToAddress(response.body as Map<String, Any>)
            } else {
                throw IllegalArgumentException("Erro ao buscar detalhes do CEP: resposta inválida.")
            }
        } catch (ex: HttpClientErrorException) {
            throw IllegalArgumentException("Erro ao buscar CEP: ${ex.message}")
        }
    }

    private fun mapViaCepResponseToAddress(viaCepResponse: Map<String, Any>): Address {
        if (viaCepResponse["erro"] == true || viaCepResponse["erro"] == "true") {
            throw IllegalArgumentException("CEP não encontrado.")
        }
        return Address(
            logradouro = viaCepResponse["logradouro"] as String?,
            bairro = viaCepResponse["bairro"] as String?,
            localidade = viaCepResponse["localidade"] as String?,
            uf = viaCepResponse["uf"] as String?,
            cep = viaCepResponse["cep"] as String?
        )
    }
}
