package JuninWins.Project.service

import JuninWins.Project.model.Cliente

interface CustomerService {

    fun save(customer : Cliente) : Cliente

    fun findById(id : Long) : Cliente

    fun update(id : Long, novoCliente : Cliente) : Cliente

    fun deleteById(id : Long)

}