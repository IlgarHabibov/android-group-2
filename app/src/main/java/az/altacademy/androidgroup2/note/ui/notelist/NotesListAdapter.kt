package az.altacademy.androidgroup2.note.ui.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import az.altacademy.androidgroup2.databinding.ItemSimpleTextBinding
import az.altacademy.androidgroup2.note.data.model.NewNoteModel

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.FirestoreViewHolder>() {

    private var dataList = mutableListOf<NewNoteModel?>()

    private var onDeleteClick:( (NewNoteModel?) -> Unit)? = null

    fun setOnDeleteClick(onDeleteCLick: (NewNoteModel?) -> Unit){
        this.onDeleteClick = onDeleteCLick
    }

    fun addData(newList: List<NewNoteModel?>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }


    inner class FirestoreViewHolder(private val binding: ItemSimpleTextBinding) :
        ViewHolder(binding.root) {

            fun bind(data: NewNoteModel?){
                binding.title.text = data?.text.orEmpty()
                binding.delete.setOnClickListener {
                    onDeleteClick?.invoke(data)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirestoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpleTextBinding.inflate(inflater, parent, false)
        return FirestoreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: FirestoreViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}