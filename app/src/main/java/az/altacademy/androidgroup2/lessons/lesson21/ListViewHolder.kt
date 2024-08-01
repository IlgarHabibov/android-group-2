package az.altacademy.androidgroup2.lessons.lesson21

import androidx.recyclerview.widget.RecyclerView
import az.altacademy.androidgroup2.databinding.ItemTextCardBinding

class ListViewHolder(
    private val binding: ItemTextCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: NoteModel, onDeleteClick:() -> Unit) {
        binding.title.text = note.title
        binding.content.text = note.note
        binding.delete.setOnClickListener {
            onDeleteClick.invoke()
        }
    }

}