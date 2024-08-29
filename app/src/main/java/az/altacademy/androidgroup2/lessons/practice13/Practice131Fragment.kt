package az.altacademy.androidgroup2.lessons.practice13

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.databinding.FragmentPractice13Binding


class Practice131Fragment : Fragment() {

    private lateinit var binding: FragmentPractice13Binding
    private val viewModel by activityViewModels<Practice13VM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPractice13Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Practice13TAG", "Step 1: variable = ${viewModel.variable}")
        viewModel.variable = 200
        Log.d("Practice13TAG", "Step 2: variable = ${viewModel.variable}")

        binding.next.text = "Close"
        binding.next.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}