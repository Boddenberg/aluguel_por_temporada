package JuninWins.Project.utils

import JuninWins.Project.model.Address
import JuninWins.Project.service.ViaCEPService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun validateCEP(cep: String): String? {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ViaCEPService::class.java)
    val call: Call<Address> = service.getAddress(cep)

    try {
        val response = call.execute()

        return if (response.isSuccessful) {
            val address = response.body()
            if (address == null) {
                "CEP not founded"
            } else {
                null
            }
        } else {
            "Error on search for CEP, verify the number at CEP"
        }
    } catch (e: Exception) {
        return "Error on call api CEP"
    }
}