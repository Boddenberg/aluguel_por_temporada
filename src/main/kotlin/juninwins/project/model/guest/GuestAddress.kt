package juninwins.project.model.guest

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean

@DynamoDbBean
data class GuestAddress(

    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val cep: String
) {

}
