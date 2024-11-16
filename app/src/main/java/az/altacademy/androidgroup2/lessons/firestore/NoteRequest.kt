package az.altacademy.androidgroup2.lessons.firestore

import com.google.firebase.firestore.FieldValue

data class NoteRequest(
    val text: String? = null,
    val createdAt: FieldValue? = null
)
