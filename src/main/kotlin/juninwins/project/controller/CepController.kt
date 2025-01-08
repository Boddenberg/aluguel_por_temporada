package juninwins.project.controller

import juninwins.project.service.CepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/cep")
class CepController(private val cepService: CepService) {

    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<Any> {
        val address = cepService.getAddressByCep(cep)
        return if (address != null) {
            ResponseEntity.ok(address)
        } else {
            ResponseEntity.status(204).build()
        }
    }
}
