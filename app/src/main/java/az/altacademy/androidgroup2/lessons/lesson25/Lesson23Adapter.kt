package az.altacademy.androidgroup2.lessons.lesson25

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import az.altacademy.androidgroup2.databinding.ItemLesson25Binding

class Lesson23Adapter: RecyclerView.Adapter<Lesson23Adapter.ViewHolder>() {

    private val list = mutableListOf<PersonEntity>()

    private var onDeleteClick: ((person: PersonEntity) -> Unit)? = null

    fun onDeleteClickListener(listener: (person: PersonEntity) -> Unit){
        onDeleteClick = listener
    }

    fun addList(newList: List<PersonEntity>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemLesson25Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(person: PersonEntity){
            binding.title.text = "${person.id} ${person.name}  ${person.surname}"
            binding.delete.setOnClickListener {
                onDeleteClick?.invoke(person)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLesson25Binding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


}