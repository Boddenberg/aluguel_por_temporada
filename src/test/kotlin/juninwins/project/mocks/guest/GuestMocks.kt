package juninwins.project.mocks.guest

import juninwins.project.model.address.Address
import juninwins.project.model.guest.Guest

object GuestMocks {

    fun guestList(): List<Guest> {
        return listOf(
            Guest(
                cpf = "11122233344",
                name = "Beyoncé",
                lastName = "Knowles",
                email = "beyonce@knowles.com",
                phoneNumber = "987654321",
                address = Address(
                    logradouro = "Rua Bahia",
                    numero = "24",
                    complemento = "Casa do fundo",
                    bairro = "Seringueiras",
                    localidade = "Houston",
                    uf = "SP",
                    cep = "07190220"
                )
            ),
            Guest(
                cpf = "22233344455",
                name = "Lady",
                lastName = "Gaga",
                email = "gaga@littlemonsters.com",
                phoneNumber = "789456123",
                address = Address(
                    logradouro = "Rua Vitoria da Conquista",
                    numero = "456",
                    complemento = "Apt 9",
                    bairro = "Pimentas",
                    localidade = "Guarulhos",
                    uf = "SP",
                    cep = "04753060"
                )
            )
        )
    }

    fun guestMock(): Guest {
        return Guest(
            cpf = "99900011122",
            name = "Taylor",
            lastName = "Swift",
            email = "theerastour@allianz.com",
            phoneNumber = "11981090986",
            address = Address(
                logradouro = "Rua Jatobá",
                numero = "1989",
                complemento = "Muquifo",
                bairro = "São João",
                localidade = "Guarulhos",
                uf = "SP",
                cep = "04753060"
            )
        )
    }
}
