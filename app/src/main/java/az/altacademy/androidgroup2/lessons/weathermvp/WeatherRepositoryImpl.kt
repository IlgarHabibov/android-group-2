package az.altacademy.androidgroup2.lessons.weathermvp

import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse
import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import az.altacademy.androidgroup2.utils.apiCall
import retrofit2.Response
import retrofit2.http.Query

class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApiService
): WeatherRepository {

    override suspend fun getCurrentWeatherByCityNew(city: String): ApiState<CurrentWeatherResponse> {
        return apiCall {weatherApiService.getCurrentWeatherByCityNew(city)  }
    }
}