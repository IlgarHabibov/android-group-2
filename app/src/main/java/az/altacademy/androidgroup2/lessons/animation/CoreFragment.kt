package az.altacademy.androidgroup2.lessons.animation

import androidx.fragment.app.Fragment

open class CoreFragment<T>: Fragment() {

    protected var binding: T? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}