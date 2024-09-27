package az.altacademy.androidgroup2.lessons.lesson27

import az.altacademy.androidgroup2.lessons.weather.WeatherApiKeyInterceptor
import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {



    private var okhttpClient = OkHttpClient.Builder()
        .addInterceptor(WeatherApiKeyInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private fun createConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    private var  retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://cat-fact.herokuapp.com/")
//        .baseUrl("https://jsonplaceholder.typicode.com/")
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(createConverter())
        .client(okhttpClient)
        .build()



    fun getApiService(): ApiService{
        return  retrofit.create(ApiService::class.java)
    }

    fun getPostApiService(): PostApiService{
        return  retrofit.create(PostApiService::class.java)
    }

    fun getWeatherApiService(): WeatherApiService{
        return  retrofit.create(WeatherApiService::class.java)
    }
}