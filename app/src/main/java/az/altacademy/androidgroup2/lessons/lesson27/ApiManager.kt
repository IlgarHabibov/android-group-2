package az.altacademy.androidgroup2.lessons.lesson27

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private var  client: Retrofit = Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService{
        return  client.create(ApiService::class.java)
    }
}