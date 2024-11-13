package az.altacademy.androidgroup2.lessons.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson25.PersonDao
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.lessons.lesson27.ApiService
import az.altacademy.androidgroup2.lessons.weathermvp.WeatherRepository
import az.altacademy.androidgroup2.utils.apiCall
import az.altacademy.androidgroup2.utils.apiCallWithFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private var currentCity: String = ""

    private val _state: MutableLiveData<UIState<CurrentWeatherResponse?>> = MutableLiveData<UIState<CurrentWeatherResponse?>>()
    val state: LiveData<UIState<CurrentWeatherResponse?>> = _state


    fun obtainEvent(event: Event){
        when(event){
            is Event.GetWeatherData ->{
                getWeatherData(city = event.city)
            }
            is Event.RefreshWeatherData -> {
                getWeatherData(currentCity, true)
            }
        }
    }


    private fun getWeatherData(city: String, isRefreshing: Boolean = false) {
        currentCity = city
        showLoading(true, isRefreshing)
        viewModelScope.launch {
            val result = weatherRepository.getCurrentWeatherByCityNew(city)
            showLoading(false, isRefreshing)
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

    private fun showLoading(isLoading: Boolean, isRefreshing: Boolean){
        if (!isRefreshing){
            _state.value = UIState.Loading(isLoading)
        }
    }


    sealed interface Event{
        data class GetWeatherData(val city: String): Event
        data object RefreshWeatherData: Event
    }

}