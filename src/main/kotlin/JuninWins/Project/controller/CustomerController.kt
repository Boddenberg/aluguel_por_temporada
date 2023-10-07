package JuninWins.Project.controller

import JuninWins.Project.model.Cliente
import JuninWins.Project.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController (val customerService: CustomerService) {

    @PostMapping("/register/guest")
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
        @RequestBody updatedCustomer: Cliente
    ): ResponseEntity<Cliente> {
        return try {
            val newUpdatedCustomer = customerService.update(cpfCustomer, updatedCustomer)
            ResponseEntity.ok(newUpdatedCustomer)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }//TODO: Se tentar alterar CPF devolver exception "CPF não pode ser alterado"
    }

    @DeleteMapping("/delete/{cpf}")
    fun deleteCustomer(
        @PathVariable(name = "cpf") cpfCustomer: String)
    : ResponseEntity<String> = customerService.deleteById(cpfCustomer)
}
    /*
    * TODO: relacionar restante das models às tabelas [OK]
    *
    * TODO: Terminar CRUD
    * TODO: Validações de models
    * TODO: Tratamento de exceções
    * TODO: Começar regras de negócio
    * TODO: Rever tratamento de exceções da service!!
    */

