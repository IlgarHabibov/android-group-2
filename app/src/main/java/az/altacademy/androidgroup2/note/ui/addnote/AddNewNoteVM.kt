package az.altacademy.androidgroup2.note.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import az.altacademy.androidgroup2.lessons.firestore.NoteRequest
import az.altacademy.androidgroup2.note.data.model.NewNoteModel
import az.altacademy.androidgroup2.note.data.model.NewNoteRequest
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.core.NotesConstants
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewNoteVM @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _state: MutableLiveData<CoreUIState<State>> = MutableLiveData()
    val state: LiveData<CoreUIState<State>> = _state


    fun addNewNote(userId: String, note: String) {
        firestore.collection(NotesConstants.NOTES).document(userId)
            .collection(NotesConstants.USER_NOTES)
            .add(
                NoteRequest(
                    text = note,
                    createdAt = FieldValue.serverTimestamp()
                )
            ).addOnCompleteListener {task ->
                if (task.isSuccessful){
                    _state.value = CoreUIState.Success(State(true))
                }

            }
            .addOnFailureListener {

            }
    }

    data class State(
        val isAdded: Boolean
    )

}