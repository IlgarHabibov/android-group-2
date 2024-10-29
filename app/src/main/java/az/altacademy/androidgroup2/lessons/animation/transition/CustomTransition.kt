package az.altacademy.androidgroup2.lessons.animation.transition

import android.content.Context
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionSet

class CustomTransition(context: Context) : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
        addTransition(Fade())
        duration = 1000 // Set duration in milliseconds
    }
}