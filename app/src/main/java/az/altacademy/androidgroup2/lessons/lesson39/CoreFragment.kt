package az.altacademy.androidgroup2.lessons.lesson39

import androidx.fragment.app.Fragment

open class CoreFragment<T>: Fragment() {

    protected var binding: T? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}