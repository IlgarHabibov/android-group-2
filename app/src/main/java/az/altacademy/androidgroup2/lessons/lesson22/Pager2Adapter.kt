package az.altacademy.androidgroup2.lessons.lesson22

import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import az.altacademy.androidgroup2.databinding.LayoutPagerPageBinding

class Pager2Adapter(private val items: List<PageModel>): RecyclerView.Adapter<Pager2Adapter.PagerViewHolder>() {

    class PagerViewHolder(private val binding: LayoutPagerPageBinding): ViewHolder(binding.root){
        fun onBind(data: PageModel){
            binding.title.text = data.title
            binding.footer.text = data.footer
            binding.imageView.setImageResource(data.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutPagerPageBinding.inflate(inflater, parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}