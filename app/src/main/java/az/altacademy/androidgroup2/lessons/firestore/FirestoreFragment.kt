package az.altacademy.androidgroup2.lessons.firestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentFirestoreBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class FirestoreFragment : Fragment() {

    private lateinit var binding: FragmentFirestoreBinding

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


        FirebaseFirestore.getInstance().collection("test2").addSnapshotListener { task, error ->
            val documents = task?.documents
            val mappedDocuments = documents?.map { document ->
                val newDocument = document.toObject(MyTestStringsModel::class.java)
                newDocument?.copy(id = document.id)
            }

            val result = mappedDocuments.toString()
            binding.resultText.text = result
        }

        // delete document with id
        FirebaseFirestore.getInstance().collection("test2").document("document_id")
    }

    private fun sendTextToFirebase(text: String) {
        FirebaseFirestore.getInstance().collection("test2")
            .add(MyTestStringsModel(text)).addOnCompleteListener {

            }
    }

}