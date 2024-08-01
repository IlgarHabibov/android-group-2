package az.altacademy.androidgroup2.lessons.lesson22

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import az.altacademy.androidgroup2.databinding.LayoutPagerPageBinding

class MyPagerAdapter(private val list: List<PageModel>): PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = LayoutPagerPageBinding.inflate(inflater, container, false)
        val item = list[position]
        binding.title.text = item.title
        binding.footer.text = item.footer
        binding.imageView.setImageResource(item.icon)
        Log.d("asdasdasdSSSsd", "instantiateItem: $item")
        return binding.root
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return true
    }
}