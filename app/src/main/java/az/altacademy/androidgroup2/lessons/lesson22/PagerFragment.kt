package az.altacademy.androidgroup2.lessons.lesson22

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class PagerFragment : Fragment() {

    private lateinit var binding: FragmentPagerBinding
    private var pagerAdapter: MyPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf(
            PageModel("Camera", R.drawable.ic_menu_camera, "First"),
            PageModel("Home", R.drawable.ic_home, "Second"),
            PageModel("Profile", R.drawable.ic_profile, "Third"),
            PageModel("Events", R.drawable.ic_event, "Fourth"),
        )

        val titles = listOf("First", "Second", "Third", "Fourth")


        val pager2Adapter = Pager2Adapter(items)

        val fragmentPagerAdapter = MyFragmentStateAdapter(this)

//        binding.viewPager2.adapter = pager2Adapter
        binding.viewPager2.adapter = fragmentPagerAdapter

        val mediator = TabLayoutMediator(
            binding.tabLayout, binding.viewPager2
        ){ tab, position ->
            tab.text = titles[position]
        }
        mediator.attach()

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                Toast.makeText(requireContext(), "position = $position", Toast.LENGTH_SHORT).show()
                Log.d("asdasdasdasdasd", "onPageSelected: $position")
                super.onPageSelected(position)
            }

        })



    }


}