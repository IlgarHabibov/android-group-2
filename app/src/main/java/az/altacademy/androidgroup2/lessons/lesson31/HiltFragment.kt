package az.altacademy.androidgroup2.lessons.lesson31

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentHiltBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class HiltFragment : Fragment(R.layout.fragment_first) {

    private val viewModel by viewModels<HiltViewmodel>()
    private lateinit var binding: FragmentHiltBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHiltBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startEngineButton.setOnClickListener {
            viewModel.startCar()
        }
    }

}