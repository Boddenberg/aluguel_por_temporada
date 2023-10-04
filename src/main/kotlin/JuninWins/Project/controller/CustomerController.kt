package JuninWins.Project.controller

import JuninWins.Project.model.Cliente
import JuninWins.Project.repository.CustomerRepository
import JuninWins.Project.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController (val customerService: CustomerService){

    @PostMapping("/register")
    fun saveCustomer(@RequestBody cliente: Cliente) : ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.save(cliente))
    }

    @GetMapping("/search/customer/{id}")
    fun findCustomer(@PathVariable (name = "id") idCustomer : Long) : ResponseEntity<Cliente> {
        return ResponseEntity.ok(customerService.findById(idCustomer))
    }

    /*
    * TODO: Terminar CRUD
    * TODO: Validações de models
    * TODO: Tratamento de exceções
    * TODO: relacionar restante das models às tabelas
    * TODO: Começar regras de negócio
    */

}