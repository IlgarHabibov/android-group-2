package az.altacademy.androidgroup2.note.data.model

import com.google.firebase.firestore.FieldValue

data class NewNoteRequest(
    val text: String? = null,
    val createdAt: FieldValue? = null
)
