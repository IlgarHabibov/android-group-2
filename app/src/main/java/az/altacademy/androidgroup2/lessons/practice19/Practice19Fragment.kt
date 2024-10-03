package az.altacademy.androidgroup2.lessons.practice19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentPractice19Binding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Practice19Fragment : Fragment() {

    private val viewModel by viewModels<Practice19VM> ()
    private lateinit var binding: FragmentPractice19Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPractice19Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       viewModel.startFlow()

        viewModel.state.observe(viewLifecycleOwner){data ->
            binding.label.text = data
        }

    }

}