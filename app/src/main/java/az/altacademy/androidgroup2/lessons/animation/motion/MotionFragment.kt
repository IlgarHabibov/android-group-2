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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            centerText.setOnClickListener {
                motionLayout.currentState
                if (motionLayout.currentState == motionLayout.startState) {
                    motionLayout.transitionToEnd()
                } else {
                    motionLayout.transitionToStart()
                }
            }
        }


    }


}