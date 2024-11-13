package az.altacademy.androidgroup2.lessons.weathermvp

interface WeatherPresenter {
    fun attachView(view: WeatherView)
    fun getWeatherData(city: String)
    fun deAttachView()
}