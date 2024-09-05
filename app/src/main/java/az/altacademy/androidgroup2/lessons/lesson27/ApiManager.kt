package az.altacademy.androidgroup2.lessons.lesson27

import android.util.Log
import az.altacademy.androidgroup2.lessons.lesson29.LogInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {



    private var okhttpClient = OkHttpClient.Builder()
        .addInterceptor(LogInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private var  client: Retrofit = Retrofit.Builder()
        .baseUrl("https://cat-fact.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()



    fun getApiService(): ApiService{
        return  client.create(ApiService::class.java)
    }
}