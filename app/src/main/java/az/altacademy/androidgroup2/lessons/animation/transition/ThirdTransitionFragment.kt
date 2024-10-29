package az.altacademy.androidgroup2.lessons.animation.transition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentFirstTransitionBinding
import az.altacademy.androidgroup2.databinding.FragmentThirdTransitionBinding


class ThirdTransitionFragment : Fragment() {

    private lateinit var binding: FragmentThirdTransitionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transition = CustomTransition(requireContext())
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition

        ViewCompat.setTransitionName(binding.logoImage, "android_logo_new")

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}