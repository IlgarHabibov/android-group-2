package az.altacademy.androidgroup2.lessons.weathermvp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.databinding.FragmentWeatherBinding
import az.altacademy.androidgroup2.lessons.lesson31.Car
import az.altacademy.androidgroup2.lessons.lesson32.di.Engine200
import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class WeatherMVPFragment : Fragment(), WeatherView, ConfigUpdateListener {

    private lateinit var binding: FragmentWeatherBinding

    @Inject
    lateinit var presenter: WeatherPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getWeatherData("London")
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.getWeatherData("London")
        }

        val config = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        config.setConfigSettingsAsync(configSettings)

        config.fetchAndActivate()
        config.addOnConfigUpdateListener(this)


    }

    override fun onSuccess(data: CurrentWeatherResponse?) {
        with(binding){
            binding.swipeRefreshLayout.isRefreshing = false
            labelCity.text = data?.location?.name.toString()
            labelTemperature.text = data?.current?.temperature.toString()
            labelStatus.text = data?.current?.condition?.text
        }
    }

    override fun onError(errorMessage: String?) {
        binding.swipeRefreshLayout.isRefreshing = false
        Toast.makeText(requireContext(), "Error: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(isLoading: Boolean) {
        binding.loading.isVisible = isLoading
    }

    override fun onUpdate(configUpdate: ConfigUpdate) {
        val showMessage = FirebaseRemoteConfig.getInstance().getBoolean("showMessage")
        Toast.makeText(requireContext(), "result ->$showMessage", Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: FirebaseRemoteConfigException) {
    }
}