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

//    @Engine200
//    @Inject
//    lateinit var car: Car

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        car.startEngine()
        viewModel.state.observe(viewLifecycleOwner){state ->
            when(state){
                is UIState.Success ->{
                    binding.labelCity.text = state.data?.location?.name.toString()
                    binding.labelTemperature.text = state.data?.current?.temperature.toString()
                    binding.labelStatus.text = state.data?.current?.condition?.text

                }
                is UIState.Error -> {
                    Toast.makeText(requireContext(), "${state.errorCode} : ${state.errorMessage}", Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading ->{
                    binding.loading.isVisible = state.isLoading
                }
            }
        }

//        binding.swipeRefreshLayout.setOnRefreshListener {
//            viewModel.getWeatherData("London", withLoading = false)
//        }
//
        viewModel.getWeatherData("Baku")
//        viewModel.getForecastDays("Baku", 10)
//
//        viewModel.dataCurrent.observe(viewLifecycleOwner) { dataCurrent ->
//            binding.labelCity.text = dataCurrent?.location?.name.toString()
//            binding.labelTemperature.text = dataCurrent?.current?.temperature.toString()
//            binding.labelStatus.text = dataCurrent?.current?.condition?.text
//            binding.swipeRefreshLayout.isRefreshing = false
//        }
//
//        viewModel.dataDays.observe(viewLifecycleOwner) { dataDays ->
//            Log.d("asdasdasdasd", "onViewCreated: ${dataDays.size}")
//            dataDays.forEach {
//                Log.d("asdasdasdasd", "onViewCreated: ${it.day.condition.text}")
//            }
//            binding.labelDays.text = dataDays.map { it.day.condition.text }.joinToString(", ")
//        }
//
//        viewModel.loading.observe(viewLifecycleOwner) { loading ->
//            binding.loading.isVisible = loading
//        }
//
//        viewModel.error.observe(viewLifecycleOwner) { error ->
//            Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show()
//        }
    }

}