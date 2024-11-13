package az.altacademy.androidgroup2.lessons.weathermvp

import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.Query

interface WeatherRepository {
    suspend fun getCurrentWeatherByCityNew(city: String, ): ApiState<CurrentWeatherResponse>
}