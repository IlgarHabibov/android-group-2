package az.altacademy.androidgroup2.lessons.weather

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json")
    fun getCurrentWeatherByCity(
        @Query("q") city: String,
    ): Call<CurrentWeatherResponse>


    @GET("current.json")
    suspend fun getCurrentWeatherByCityNew(
        @Query("q") city: String,
    ): Response<CurrentWeatherResponse>

    @GET("v1/forecast.json")
    suspend fun getDailyWeather(
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<CurrentWeatherResponse>


}