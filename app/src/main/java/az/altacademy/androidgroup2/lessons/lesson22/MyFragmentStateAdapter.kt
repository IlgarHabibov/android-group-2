package az.altacademy.androidgroup2.lessons.lesson22

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import az.altacademy.androidgroup2.fragments.DetailsFragment
import az.altacademy.androidgroup2.fragments.FirstFragment

class MyFragmentStateAdapter(
//    private val fragmentManager: FragmentManager,
//    private val  lifecycle: Lifecycle,
    private val  fragment: Fragment,

): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> DetailsFragment()
            else -> FirstFragment()
        }
    }

}