package az.altacademy.androidgroup2.lessons.firestore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import az.altacademy.androidgroup2.databinding.ItemSimpleTextBinding

class FirestoreAdapter : RecyclerView.Adapter<FirestoreAdapter.FirestoreViewHolder>() {

    private var dataList = mutableListOf<NoteModel?>()

    private var onDeleteClick:( (NoteModel?) -> Unit)? = null

    fun setOnDeleteClick(onDeleteCLick: (NoteModel?) -> Unit){
        this.onDeleteClick = onDeleteCLick
    }

    fun addData(newList: List<NoteModel?>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }


    inner class FirestoreViewHolder(private val binding: ItemSimpleTextBinding) :
        ViewHolder(binding.root) {

            fun bind(data: NoteModel?){
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