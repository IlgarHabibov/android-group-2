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

//        viewModel.state.observe(viewLifecycleOwner){state ->
//            when(state){
//                is UIState.Success ->{
//                    binding.labelCity.text = state.data?.location?.name.toString()
//                    binding.labelTemperature.text = state.data?.current?.temperature.toString()
//                    binding.labelStatus.text = state.data?.current?.condition?.text
//
//                }
//                is UIState.Error -> {
//                    Toast.makeText(requireContext(), "${state.errorCode} : ${state.errorMessage}", Toast.LENGTH_SHORT).show()
//                }
//                is UIState.Loading ->{
//                    binding.loading.isVisible = state.isLoading
//                }
//            }
//        }

        viewModel.getWeatherData("Baku")
        viewModel.getForecastDays("Baku", 10)

        viewModel.dataCurrent.observe(viewLifecycleOwner) { dataCurrent ->
            binding.labelCity.text = dataCurrent?.location?.name.toString()
            binding.labelTemperature.text = dataCurrent?.current?.temperature.toString()
            binding.labelStatus.text = dataCurrent?.current?.condition?.text
        }

        viewModel.dataDays.observe(viewLifecycleOwner) { dataDays ->
            Log.d("asdasdasdasd", "onViewCreated: ${dataDays.size}")
            dataDays.forEach {
                Log.d("asdasdasdasd", "onViewCreated: ${it.day.condition.text}")
            }
            binding.labelDays.text = dataDays.map { it.day.condition.text }.joinToString(", ")
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.loading.isVisible = loading
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show()
        }
    }

}