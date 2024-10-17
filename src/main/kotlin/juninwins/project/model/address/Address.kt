package juninwins.project.model.address

import com.fasterxml.jackson.annotation.JsonInclude
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
data class Address(

    @get:DynamoDbAttribute("logradouro")
    val logradouro: String? = null,

    @get:DynamoDbAttribute("numero")
    val numero: String? = null,

    @get:DynamoDbAttribute("complemento")
    val complemento: String? = null,

    @get:DynamoDbAttribute("bairro")
    val bairro: String? = null,

    @get:DynamoDbAttribute("localidade")
    val localidade: String? = null,

    @get:DynamoDbAttribute("uf")
    val uf: String? = null,

    @get:DynamoDbAttribute("cep")
    val cep: String? = null
)
