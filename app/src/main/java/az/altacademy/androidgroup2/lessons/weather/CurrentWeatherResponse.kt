package az.altacademy.androidgroup2.lessons.weather

import com.google.gson.annotations.SerializedName



data class CurrentWeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val country: String,
    @SerializedName("localtime")
    val localtime: String,

    )

data class Current(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val temperature: Double,
    val humidity: Int,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("feelslike_c")
    val feelsLike: Double,
    @SerializedName("is_day")
    val isDay: Int,
    val condition: Condition
)


data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>

)


data class ForecastDay(
    @SerializedName("date")
    val date: String,
    val day: Day,


    )

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Day(
    @SerializedName("avgtemp_c")
    val temperatureAv: Double,
    @SerializedName("avghumidity")
    val humidityAv: Int,
    @SerializedName("maxwind_kph")
    val windKphMax: Double,
    val condition: Condition


)
