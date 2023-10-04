package JuninWins.Project.testDB

import JuninWins.Project.model.Cliente
import JuninWins.Project.model.Endereco
import JuninWins.Project.repository.ClienteRepository
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigurationClazzGenerateData(val clienteRepository: ClienteRepository) {
    val logger = LoggerFactory.getLogger("data")
    @PostConstruct
    fun loadData() {
        clienteRepository.save(
            Cliente(
                1,
                "nome- teste",
                "sobrenome- teste",
                "teste@gmail.com",
                "55-55555-5555",
                "10/08/1999",
                "666-666-666-66",
                true,
                Endereco(1, "logradouro", "300", "esquina", "bairro", "SP", "SP", "3124124-22")
            )
        )

        clienteRepository.findAll().forEach { cliente ->
            logger.info(cliente.toString())
        }
    }
}