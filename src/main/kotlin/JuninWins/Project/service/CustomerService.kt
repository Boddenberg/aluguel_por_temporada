package JuninWins.Project.service

import JuninWins.Project.model.Cliente

interface CustomerService {

    fun save(customer : Cliente) : Cliente

    fun findById(cpf : String) : Cliente

    fun update(cpf : String, newCustomer : Cliente) : Cliente

    fun deleteById(cpf : String)

}