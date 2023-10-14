package JuninWins.Project.service

import JuninWins.Project.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

fun interface ViaCEPService {
    @GET("{cep}/json/")
    fun getAddress(@Path("cep") cep: String): Call<Address>
}