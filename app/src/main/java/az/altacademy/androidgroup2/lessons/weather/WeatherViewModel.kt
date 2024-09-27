package az.altacademy.androidgroup2.lessons.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson25.PersonDao
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.lessons.lesson27.ApiService
import az.altacademy.androidgroup2.utils.apiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (
    private val weatherApiService: WeatherApiService
) : ViewModel() {

    private val _state: MutableLiveData<UIState<CurrentWeatherResponse?>> = MutableLiveData<UIState<CurrentWeatherResponse?>>()
    val state: LiveData<UIState<CurrentWeatherResponse?>> = _state

    fun getWeatherData(city: String) {
        _state.value = UIState.Loading(true)
        viewModelScope.launch {
            val result = apiCall { weatherApiService.getCurrentWeatherByCityNew(city) }
            _state.value = UIState.Loading(false)
            when(result){
                is ApiState.Success -> {
                    _state.value = UIState.Success(result.data)
                }
                is ApiState.Error -> {
                    _state.value = UIState.Error(result.error?.code, result.error?.message)
                }
            }
        }
    }

//    private val _dataCurrent: MutableLiveData<CurrentWeatherResponse?> = MutableLiveData<CurrentWeatherResponse?>()
//    val dataCurrent: LiveData<CurrentWeatherResponse?> = _dataCurrent
//
//    private val _dataDays: MutableLiveData<List<ForecastDay>> = MutableLiveData<List<ForecastDay>>()
//    val dataDays: LiveData<List<ForecastDay>> = _dataDays
//
//    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//    val loading: LiveData<Boolean> = _loading
//
//    private val _error: MutableLiveData<String?> = MutableLiveData<String?>()
//    val error: LiveData<String?> = _error

//    fun getWeatherData(city: String, withLoading: Boolean = true) {
//        if (withLoading) _loading.value = true
//        viewModelScope.launch {
//            val result = apiCall { ApiManager.getWeatherApiService().getCurrentWeatherByCityNew(city) }
//            _loading.value = false
//            when(result){
//                is ApiState.Success -> {
//                    _dataCurrent.value = result.data
//                }
//                is ApiState.Error -> {
//                    _error.value = result.error?.message
//                }
//            }
//        }
//    }
//
//    fun getForecastDays(city: String, dayCount: Int){
//        _loading.value = true
//        viewModelScope.launch {
//            val result = apiCall { ApiManager.getWeatherApiService().getDailyWeather(city, dayCount) }
//            _loading.value = false
//            when(result){
//                is ApiState.Success -> {
//                    _dataDays.value = result.data?.forecast?.forecastDay
//                }
//                is ApiState.Error -> {
//                    _error.value = result.error?.message
//                }
//            }
//        }
//    }

}