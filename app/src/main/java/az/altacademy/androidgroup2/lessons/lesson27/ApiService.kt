package az.altacademy.androidgroup2.lessons.lesson27

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("facts")
    fun getFacts(): Call<List<FactsResponse>>

    @GET("facts/{id}")
    fun getFactById(@Path("id") id: String?): Call<FactResponse>

}