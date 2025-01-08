package juninwins.project.service.impl

import juninwins.project.model.address.Address
import juninwins.project.service.CepService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CepServiceImpl : CepService {

    private val restTemplate = RestTemplate()

    override fun getAddressByCep(cep: String): Address? {
        val url = "https://viacep.com.br/ws/$cep/json/"
        val response = restTemplate.getForEntity(url, Map::class.java)

        if (response.statusCode.is2xxSuccessful && response.body != null) {
            val viaCepResponse = response.body as Map<String, Any>
            return if (viaCepResponse["erro"] == true || viaCepResponse["erro"] == "true") {
                null
            } else {
                mapViaCepResponseToAddress(viaCepResponse)
            }
        }
        return null
    }

    private fun mapViaCepResponseToAddress(viaCepResponse: Map<String, Any>): Address {
        return Address(
            logradouro = viaCepResponse["logradouro"] as String?,
            bairro = viaCepResponse["bairro"] as String?,
            localidade = viaCepResponse["localidade"] as String?,
            uf = viaCepResponse["uf"] as String?,
            cep = viaCepResponse["cep"] as String?
        )
    }
}
