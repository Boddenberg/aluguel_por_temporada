package juninwins.project.service

import juninwins.project.model.address.Address

interface CepService {
    fun getAddressByCep(cep: String): Address?
}