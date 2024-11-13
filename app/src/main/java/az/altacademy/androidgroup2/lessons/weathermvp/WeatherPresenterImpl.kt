package az.altacademy.androidgroup2.lessons.weathermvp

import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.UIState
import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import az.altacademy.androidgroup2.utils.apiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class WeatherPresenterImpl @Inject  constructor(
    private val weatherRepository: WeatherRepository
): WeatherPresenter, CoroutineScope {

    private var view: WeatherView? = null

    override fun attachView(view: WeatherView) {
        this.view = view

    }

    override fun getWeatherData(city: String) {
        view?.onLoading(true)
        launch {
            val result = weatherRepository.getCurrentWeatherByCityNew(city)
            view?.onLoading(false)
            when(result){
                is ApiState.Success -> {
                    view?.onSuccess(result.data)
                }
                is ApiState.Error -> {
                    view?.onError(result.error?.message)
                }
            }
        }

    }

    override fun deAttachView() {
        cancel()
        view = null
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()
}