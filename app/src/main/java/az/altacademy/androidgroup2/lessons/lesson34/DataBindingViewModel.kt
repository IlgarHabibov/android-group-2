package az.altacademy.androidgroup2.lessons.lesson34

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson25.PersonDao
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.lessons.lesson27.ApiService
import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse
import az.altacademy.androidgroup2.lessons.weather.UIState
import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import az.altacademy.androidgroup2.utils.apiCall
import az.altacademy.androidgroup2.utils.apiCallWithFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataBindingViewModel @Inject constructor (
    private val weatherApiService: WeatherApiService
) : ViewModel() {

    var city = "Baku"

    private val _data: MutableLiveData<CurrentWeatherResponse?> = MutableLiveData<CurrentWeatherResponse?>()
    val data: LiveData<CurrentWeatherResponse?> = _data


    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String?> = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun getWeatherData() {
        Log.d("asdasdasdasd", "getWeatherData: ")
        _loading.value = true
        viewModelScope.launch {
            val result = apiCall { weatherApiService.getCurrentWeatherByCityNew(city) }
            _loading.value = false
            when(result){
                is ApiState.Success -> {
                    _data.value = result.data
                }
                is ApiState.Error -> {
                    _error.value = result.error?.message
                }
            }
        }
    }

}