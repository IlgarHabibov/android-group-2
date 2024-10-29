package az.altacademy.androidgroup2.lessons.animation.transition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentFirstTransitionBinding
import az.altacademy.androidgroup2.extensions.navigateWithFadeAnimation
import az.altacademy.androidgroup2.extensions.navigateWithSlideAnimation


class FirstTransitionFragment : Fragment() {

    private lateinit var binding: FragmentFirstTransitionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener{
//
            val extra = FragmentNavigatorExtras(
                binding.logoImage to "android_logo_new"
            )

            val transition = CustomTransition(requireContext())

//            val transition2 = TransitionSet().apply {
//                ordering = TransitionSet.ORDERING_TOGETHER
//                addTransition(ChangeBounds())
//                addTransition(Fade())
//                this.duration = duration // Set the duration here
//            }
            sharedElementEnterTransition = transition
            sharedElementReturnTransition = transition
//
////            sharedElementEnterTransition = SharedElementTransitionHelper.createMaterialContainerTransform(requireContext())
////            sharedElementReturnTransition = SharedElementTransitionHelper.createMaterialContainerTransform(requireContext())
////
////            sharedElementEnterTransition = SharedElementTransitionHelper.createSharedElementTransition()
////            sharedElementReturnTransition = SharedElementTransitionHelper.createSharedElementTransition()
//
            ViewCompat.setTransitionName( binding.logoImage, "android_logo_new")
//
//
            findNavController().navigate(
                R.id.action_firstTransitionFragment_to_secondTransitionFragment,
                null,
                null,
//                FirstTransitionFragmentDirections.actionFirstTransitionFragmentToSecondTransitionFragment(),
                extra
            )

//            findNavController().navigate(
//                FirstTransitionFragmentDirections.actionFirstTransitionFragmentToSecondTransitionFragment(),
//                extra
//            )

//            findNavController().navigateWithSlideAnimation(
//                FirstTransitionFragmentDirections.actionFirstTransitionFragmentToSecondTransitionFragment()
//            )

        }
    }

}


