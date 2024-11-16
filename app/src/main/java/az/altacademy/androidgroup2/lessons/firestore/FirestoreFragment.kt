package az.altacademy.androidgroup2.lessons.firestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.altacademy.androidgroup2.databinding.FragmentFirestoreBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class FirestoreFragment : Fragment() {

    private lateinit var binding: FragmentFirestoreBinding
    private val adapter = FirestoreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirestoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val text = binding.textInput.text.toString()
            sendTextToFirebase(text)
            binding.textInput.text = null
        }

        binding.noteRecycler.adapter = adapter

        adapter.setOnDeleteClick { note ->
            note?.id?.let {id ->
                FirebaseFirestore.getInstance().collection("test2").document(id).delete()
            }
        }


        FirebaseFirestore.getInstance()
            .collection("test2")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { task, error ->
            val documents = task?.documents
            val mappedDocuments = documents?.map { document ->
                val newDocument = document.toObject(NoteModel::class.java)
                newDocument?.copy(id = document.id)
            }

            addDataToAdapter(mappedDocuments.orEmpty())
        }

        // delete document with id

    }

    private fun addDataToAdapter(list: List<NoteModel?>){
        Log.d("asdasdASdasdasd", "list: ${list.toString()}")
        adapter.addData(list)
    }

    private fun sendTextToFirebase(text: String) {
        FirebaseFirestore.getInstance().collection("test2")
            .add(NoteRequest(
                text = text,
                createdAt = FieldValue.serverTimestamp()
            )).addOnCompleteListener {

            }
    }

}