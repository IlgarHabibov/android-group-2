package az.altacademy.androidgroup2.note.data.model

import com.google.firebase.Timestamp

data class NewNoteModel(
    val id: String? = null,
    val text: String? = null,
    val createdAt: Timestamp? = null
)
