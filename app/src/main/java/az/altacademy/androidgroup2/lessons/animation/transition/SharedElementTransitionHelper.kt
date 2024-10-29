package az.altacademy.androidgroup2.lessons.animation.transition

import android.content.Context
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.google.android.material.transition.MaterialContainerTransform

object SharedElementTransitionHelper {
    fun createMaterialContainerTransform(context: Context, duration: Long = 3600): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            this.duration = duration
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            setAllContainerColors(context.getColor(android.R.color.background_light)) // Set default background
        }
    }

    fun createSharedElementTransition(duration: Long = 1000): TransitionSet {
        return TransitionSet().apply {
            ordering = TransitionSet.ORDERING_TOGETHER
            addTransition(ChangeBounds())
            addTransition(Fade())
            this.duration = duration // Set the duration here
        }
    }
}