package az.altacademy.androidgroup2.lessons.weathermvp

import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse

interface WeatherView {
    fun onSuccess(data: CurrentWeatherResponse?)
    fun onError(errorMessage: String?)
    fun onLoading(isLoading: Boolean)
}