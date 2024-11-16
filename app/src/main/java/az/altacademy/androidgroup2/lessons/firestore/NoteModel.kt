package az.altacademy.androidgroup2.lessons.firestore

import com.google.firebase.Timestamp

data class NoteModel(
    val id: String? = null,
    val text: String? = null,
    val createdAt: Timestamp? = null
)
