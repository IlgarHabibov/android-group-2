package az.altacademy.androidgroup2.lessons.animation.motion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentMotionBinding
import az.altacademy.androidgroup2.lessons.animation.CoreFragment

class MotionFragment : Fragment() {
    private lateinit var binding: FragmentMotionBinding

    private var stateId = R.id.endOfMotion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.motionLayout.setTransition(R.id.startOfMotion, R.id.endOfMotion)
        binding.centerText.setOnClickListener {
            binding.motionLayout.transitionToEnd()
        }

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener{

            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                stateId = triggerId
            }

        })
    }


}