package az.altacademy.androidgroup2.note.ui.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentAddNoteBinding
import az.altacademy.androidgroup2.databinding.FragmentNoteLoginBinding
import az.altacademy.androidgroup2.databinding.FragmentNoteRegisterBinding
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.register.NoteRegisterVM
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNewNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel by viewModels<AddNewNoteVM>()


    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner){state->
            onStateChange(state)
        }


        binding.save.setOnClickListener {
            val note = binding.noteInput.text.toString()
            firebaseAuth.currentUser?.uid?.let { userId->
                viewModel.addNewNote(userId, note)
            }
        }
    }

    private fun onStateChange(state: CoreUIState<AddNewNoteVM.State>){
        when(state){
            is CoreUIState.Success ->{
                onSuccess(state.data)
            }
            is CoreUIState.Loading ->{}
            is CoreUIState.Error ->{
                Toast.makeText(requireContext(), "${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onSuccess(data: AddNewNoteVM.State) {
        if (data.isAdded)
            findNavController().popBackStack()
    }

}