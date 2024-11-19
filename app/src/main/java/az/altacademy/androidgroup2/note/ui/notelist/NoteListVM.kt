package az.altacademy.androidgroup2.note.ui.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import az.altacademy.androidgroup2.note.data.model.NewNoteModel
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.core.NotesConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListVM @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _state: MutableLiveData<CoreUIState<State>> = MutableLiveData()
    val state: LiveData<CoreUIState<State>> = _state


    fun getUserNotes(userId: String) {
        firestore.collection(NotesConstants.NOTES).document(userId)
            .collection(NotesConstants.USER_NOTES)
            .orderBy(NotesConstants.CREATED_AT, Query.Direction.DESCENDING)
            .addSnapshotListener { task, error ->
                val documents = task?.documents
                val mappedDocuments = documents?.map { document ->
                    val newDocument = document.toObject(NewNoteModel::class.java)
                    newDocument?.copy(id = document.id)
                }
                _state.value = CoreUIState.Success(State(mappedDocuments.orEmpty()))
            }
    }

    data class State(
        val noteList: List<NewNoteModel?>
    )

}