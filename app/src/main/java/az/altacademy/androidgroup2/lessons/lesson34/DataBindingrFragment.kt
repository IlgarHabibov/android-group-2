package az.altacademy.androidgroup2.lessons.lesson34

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentDatabindingBinding
import az.altacademy.androidgroup2.databinding.FragmentWeatherBinding
import az.altacademy.androidgroup2.lessons.lesson31.Car
import az.altacademy.androidgroup2.lessons.lesson32.di.Engine200
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class DataBindingrFragment : Fragment() {
    private lateinit var binding: FragmentDatabindingBinding
    private val viewModel by viewModels<DataBindingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentDatabindingBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_databinding, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val c =  arguments?.let { arg->
            val a = arg.getString("A")
        }


        binding.weatherViewmodel = viewModel


        viewModel.data.observe(viewLifecycleOwner){ data ->
            data?.current?.condition?.text?.let {

            }

            binding.weatherData = data
        }
        viewModel.error.observe(viewLifecycleOwner) {
            // not implement
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.loading.isVisible = isLoading
        }

        viewModel.city = "London"
//        viewModel.getWeatherData()
    }

}