package az.altacademy.androidgroup2.lessons.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiManager.getWeatherApiService().getCurrentWeatherByCity(city = "Baku").enqueue(object: Callback<CurrentWeatherResponse>{
            override fun onResponse(
                p0: Call<CurrentWeatherResponse>,
                p1: Response<CurrentWeatherResponse>
            ) {
            }

            override fun onFailure(p0: Call<CurrentWeatherResponse>, p1: Throwable) {
            }

        })
    }

}