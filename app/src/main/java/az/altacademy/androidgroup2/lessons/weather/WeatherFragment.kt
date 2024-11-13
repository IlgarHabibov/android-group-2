package az.altacademy.androidgroup2.lessons.weather

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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.obtainEvent(WeatherViewModel.Event.GetWeatherData("Baku"))

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.obtainEvent(WeatherViewModel.Event.RefreshWeatherData)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Success -> {
                    hideRefresh()
                    with(binding) {
                        labelCity.text = state.data?.location?.name.toString()
                        labelTemperature.text = state.data?.current?.temperature.toString()
                        labelStatus.text = state.data?.current?.condition?.text
                    }
                }

                is UIState.Error -> {
                    hideRefresh()
                    Toast.makeText(
                        requireContext(),
                        "${state.errorCode} : ${state.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UIState.Loading -> {
                    binding.loading.isVisible = state.isLoading
                }
            }
        }
    }

    private fun hideRefresh(){
        binding.swipeRefreshLayout.isRefreshing = false
    }

}