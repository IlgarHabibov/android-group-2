package az.altacademy.androidgroup2.lessons.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {


    private val _state: MutableLiveData<ApiState> = MutableLiveData<ApiState>()
    val state: LiveData<ApiState> = _state

    fun getWeatherData(city: String) {
        _state.value = ApiState.Loading(true)


        Log.d("asdasdasdasdas", "onResponse 0: ")

        ApiManager.getWeatherApiService().getCurrentWeatherByCity(city).enqueue(object :
            Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                Log.d("asdasdasdasdas", "onResponse 1: ")
               if (response.isSuccessful){
                   _state.value = ApiState.Success(data = response.body())
               }else {
                   _state.value = ApiState.Error(errorMessage = "Xeta bas verdi")
               }
                _state.value = ApiState.Loading(false)
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                _state.value = ApiState.Error(errorMessage = t.localizedMessage)
                _state.value = ApiState.Loading(false)
            }

        })

        Log.d("asdasdasdasdas", "onResponse 2: ")
    }

    suspend fun start(){
        test1()
    }

    suspend fun test1(){

    }
}