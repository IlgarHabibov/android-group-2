package az.altacademy.androidgroup2.lessons.lesson39

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentSimpleAnimationsBinding

class SimpleAnimationsFragment : CoreFragment<FragmentSimpleAnimationsBinding>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimpleAnimationsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.rectangleBlue?.setOnClickListener {
            startAutoTransitionAnimation(binding?.rectangleBlue)
        }
    }

    private fun startAlphaAnimation(view: View?){
        val alphaAnimation = AlphaAnimation(1.0f, 0f).apply {
            duration = 500
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }

        alphaAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationEnd(animation: Animation?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationRepeat(animation: Animation?) {
                TODO("Not yet implemented")
            }

        })
        view?.startAnimation(alphaAnimation)
//        AnimationUtils.loadAnimation(requireContext(), R.anim.alpha).also { animation ->
//            view?.startAnimation(animation)
//        }

    }

    private fun startScaleAnimation(view: View?){
        val scaleAnimation = ScaleAnimation(1f, 1.2f, 1.0f, 1.2f,
        ).apply {
            duration = 2000
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }
        view?.startAnimation(scaleAnimation)
    }

    private fun startRotateAnimation(view: View?){
        val scaleAnimation = RotateAnimation(
            360f,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
        ).apply {
            duration = 2000
            repeatCount = 19
            repeatMode = Animation.REVERSE
        }
        view?.startAnimation(scaleAnimation)
    }

    private fun startTranslateAnimation(view: View?){
        val scaleAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            -0.5f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
        ).apply {
            duration = 2000
//            repeatCount = 19
//            repeatMode = Animation.REVERSE
        }
        view?.startAnimation(scaleAnimation)
    }


    private fun startColorAnimation(view: View?){
        val animation = ObjectAnimator.ofInt(
            view,
            "backgroundColor",
            ContextCompat.getColor(requireContext(), R.color.blue),
            ContextCompat.getColor(requireContext(), R.color.red),
        )

        animation.setEvaluator(ArgbEvaluator())
        animation.duration = 300
        animation.repeatMode = ObjectAnimator.REVERSE
        animation.repeatCount = ObjectAnimator.INFINITE

        animation.start()
    }

    private fun startTextColorAnimation(textView: TextView?){
        val animation = ObjectAnimator.ofInt(
            textView,
            "textColor",
            ContextCompat.getColor(requireContext(), R.color.blue),
            ContextCompat.getColor(requireContext(), R.color.red),
        )

        animation.setEvaluator(ArgbEvaluator())
        animation.duration = 300
        animation.repeatMode = ObjectAnimator.REVERSE
        animation.repeatCount = ObjectAnimator.INFINITE

        animation.start()
    }


    private fun startWidthAnimation(view: View?){

        val initialWidth = view?.width ?: 0
        val dimen = resources.getDimensionPixelSize(R.dimen.dimen_10dp)
        val newWidth = initialWidth + dimen

        val animation = ValueAnimator.ofInt(
            initialWidth, newWidth
        )

        animation.addUpdateListener { animator ->
            val newValue = animator.animatedValue as Int
            binding?.animationText?.text = newValue.toString()
            val params = view?.layoutParams as? ConstraintLayout.LayoutParams
            params?.width = newValue
            view?.layoutParams = params
        }
        animation.duration = 1000
//        animation.repeatMode = ObjectAnimator.REVERSE
//        animation.repeatCount = ObjectAnimator.INFINITE

        animation.start()
    }


    private fun startMarginAnimation(view: View?){
        val initialWidth = view?.marginTop ?: 0
        val dimen = resources.getDimensionPixelSize(R.dimen.dimen_10dp)
        val newWidth = initialWidth + dimen

        val animation = ValueAnimator.ofInt(
            initialWidth, newWidth
        )

        animation.addUpdateListener { animator ->
            val newValue = animator.animatedValue as Int
            val params = view?.layoutParams as? ConstraintLayout.LayoutParams
            params?.topMargin = newValue
            view?.layoutParams = params
        }
        animation.duration = 1000
//        animation.repeatMode = ObjectAnimator.REVERSE
//        animation.repeatCount = ObjectAnimator.INFINITE

        animation.start()
    }

    private fun startAutoTransitionAnimation(view: View?){
        val transition = AutoTransition()
        transition.duration = 1000
        binding?.root?.let {root ->
            TransitionManager.beginDelayedTransition(root, transition)
            view?.visibility = View.INVISIBLE
        }

    }


}