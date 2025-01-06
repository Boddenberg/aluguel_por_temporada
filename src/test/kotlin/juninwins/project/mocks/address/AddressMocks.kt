package juninwins.project.mocks.address

import juninwins.project.model.address.Address

object AddressMocks {

    fun addressMock(): Address {
        return Address(
                logradouro = "Rua Vitória da Conquista",
                numero = "141",
                complemento = "Casa dos fundos",
                bairro = "Pq das Seringueiras",
                localidade = "Guarulhos",
                uf = "SP",
                cep = "07190220"
            )
    }

}