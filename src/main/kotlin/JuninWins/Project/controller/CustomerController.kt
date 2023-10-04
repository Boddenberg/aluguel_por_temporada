package JuninWins.Project.controller

import JuninWins.Project.model.Cliente
import JuninWins.Project.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController (val customerService: CustomerService) {

    @PostMapping("/register")
    fun saveCustomer(@RequestBody @Valid cliente: Cliente): ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.save(cliente))
    }

    @GetMapping("/search/{id}")
    fun findCustomer(@PathVariable(name = "id") idCustomer: String): ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.findById(idCustomer))
    }

    @PutMapping("/update/{id}")
    fun updateCustomer(
        @PathVariable(name = "id") idCustomer: String,
        @RequestBody updatedCliente: Cliente
    ): ResponseEntity<Cliente> {
        try {
            val updatedCustomer = customerService.update(idCustomer, updatedCliente)
            return ResponseEntity.ok(updatedCustomer)
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        }
    }
}
    /*
    * TODO: Terminar CRUD
    * TODO: Validações de models
    * TODO: Tratamento de exceções
    * TODO: relacionar restante das models às tabelas
    * TODO: Começar regras de negócio
    * TODO: Rever tratamento de exceções da service!!
    */

