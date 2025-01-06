package juninwins.project.controller

import juninwins.project.model.address.Address
import juninwins.project.service.CepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/cep")
class CepController(private val cepService: CepService) {

    @GetMapping("/{cep}")
    fun getAddressByCep(@PathVariable cep: String): ResponseEntity<Address> {
        val address = cepService.getAddressByCep(cep)
        return ResponseEntity.ok(address)
    }
}
