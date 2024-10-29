package az.altacademy.androidgroup2.lessons.animation.transition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentSecondTransitionBinding


class SecondTransitionFragment : Fragment() {

    private lateinit var binding: FragmentSecondTransitionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        sharedElementEnterTransition = SharedElementTransitionHelper.createMaterialContainerTransform(requireContext())
//        sharedElementReturnTransition = SharedElementTransitionHelper.createMaterialContainerTransform(requireContext())

//        sharedElementEnterTransition = SharedElementTransitionHelper.createSharedElementTransition()
//        sharedElementReturnTransition = SharedElementTransitionHelper.createSharedElementTransition()


        val transition = CustomTransition(requireContext())
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition

        ViewCompat.setTransitionName( binding.logoImage, "android_logo_new")


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nextButton.setOnClickListener {
            val extra = FragmentNavigatorExtras(
                binding.logoImage to "android_logo_new"
            )

            findNavController().navigate(
                SecondTransitionFragmentDirections.actionSecondTransitionFragmentToThirdTransitionFragment(),
                extra
                )
        }
    }
}