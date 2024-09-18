package az.altacademy.androidgroup2.lessons.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentWeatherBinding
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        viewModel.state.observe(viewLifecycleOwner){state ->
            when(state){
                is ApiState.Success ->{
                    binding.labelCity.text = state.data?.location?.name.toString()
                    binding.labelTemperature.text = state.data?.current?.temperature.toString()
                    binding.labelStatus.text = state.data?.current?.condition?.text

                }
                is ApiState.Error -> {

                    Toast.makeText(requireContext(), "${state.errorMessage}", Toast.LENGTH_SHORT).show()

                }
                is ApiState.Loading ->{
                    binding.loading.isVisible = state.isLoading
                }
            }
        }

        viewModel.getWeatherData("Baku")
    }

}