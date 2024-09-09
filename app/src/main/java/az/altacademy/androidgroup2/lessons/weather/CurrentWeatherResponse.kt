package az.altacademy.androidgroup2.lessons.weather

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    var location: WeatherLocation,
    var current: Current
)


data class WeatherLocation(
    var name: String,
    var region: String,
    var country: String,
    var localtime: String
)

data class Current(
    @SerializedName("temp_c")
    var temperature: Double,

    @SerializedName("is_day")
    var isDay: Int,

    var condition: Condition
)

data class Condition(
    var text: String,
    var icon: String,
    var code: Int
)
