package JuninWins.Project.controller

import JuninWins.Project.model.Cliente
import JuninWins.Project.service.CustomerService
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController (val customerService: CustomerService) {

    @PostMapping("/register/customer")
    fun saveCustomer(@RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.save(cliente))
    }

    @GetMapping("/search/customer/{id}")
    fun findCustomer(@PathVariable(name = "id") idCustomer: Long): ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.findById(idCustomer))
    }


    private val modelMapper = ModelMapper()

    @PutMapping("/update/customer/{id}")
    fun updateCustomer(
        @PathVariable(name = "id") idCustomer: Long,
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

