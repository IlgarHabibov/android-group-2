package az.altacademy.androidgroup2.lessons.lesson21

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.altacademy.androidgroup2.databinding.ItemTextCardBinding

class TextListAdapter : RecyclerView.Adapter<ListViewHolder>() {

    private val list = mutableListOf<NoteModel>()

    private var onDeleteClick: ((position: Int) -> Unit)? = null


    fun addItem(items: NoteModel){
        list.add(items)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun onRemoveClickLister(listener: (position: Int) -> Unit){
        onDeleteClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTextCardBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(
            note = list[position],
            onDeleteClick = {
                onDeleteClick?.invoke(position)
            }
        )

    }

}