package JuninWins.Project.service

import JuninWins.Project.model.Cliente

interface CustomerService {

    fun save(customer : Cliente) : Cliente

    fun findById(id : String) : Cliente

    fun update(id : String, newCustomer : Cliente) : Cliente

    fun deleteById(id : String)

}