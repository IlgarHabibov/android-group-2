package az.altacademy.androidgroup2.lessons.animation.motion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentMotion2Binding

class Motion2Fragment : Fragment() {

    private lateinit var binding: FragmentMotion2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMotion2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}