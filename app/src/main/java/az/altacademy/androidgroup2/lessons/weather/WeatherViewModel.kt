package az.altacademy.androidgroup2.lessons.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.utils.apiCall
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {


    private val _state: MutableLiveData<ApiState> = MutableLiveData<ApiState>()
    val state: LiveData<ApiState> = _state

    fun getWeatherData(city: String) {
        _state.value = ApiState.Loading(true)
        viewModelScope.launch {
            val result = apiCall { ApiManager.getWeatherApiService().getCurrentWeatherByCityNew(city) }
            _state.value = ApiState.Loading(false)
            when(result){
                is ApiResult.Success -> {
                    _state.value = ApiState.Success(data = result.data)
                }
                is ApiResult.Error -> {
                    _state.value = ApiState.Error(result.error?.errorDescription)
                }
            }
        }
    }

}