package az.altacademy.androidgroup2.lessons.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json")
    fun getCurrentWeatherByCity(
        @Query("q") city: String,
    ): Call<CurrentWeatherResponse>

}