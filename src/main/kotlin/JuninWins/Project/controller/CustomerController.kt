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

    @GetMapping("/search/{cpf}")
    fun findCustomer(@PathVariable(name = "cpf") cpfCustomer: String): ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.findGuestByCPF(cpfCustomer))
    }

    @PutMapping("/update/{cpf}")
    fun updateCustomer(
        @PathVariable(name = "cpf") cpfCustomer: String,
        @RequestBody updatedCliente: Cliente
    ): ResponseEntity<Cliente> {
        try {
            val updatedCustomer = customerService.update(cpfCustomer, updatedCliente)
            return ResponseEntity.ok(updatedCustomer)
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/{cpf}")
    fun deleteCustomer(
        @PathVariable(name = "cpf") cpfCustomer: String) : ResponseEntity<String> {

            return customerService.deleteById(cpfCustomer)

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

