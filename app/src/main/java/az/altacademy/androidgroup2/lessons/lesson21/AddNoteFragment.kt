package az.altacademy.androidgroup2.lessons.lesson21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment() {

    private lateinit var bindin: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindin = FragmentAddNoteBinding.inflate(inflater, container, false)
        return bindin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = AddNoteFragmentArgs.fromBundle(arguments ?: bundleOf())

        Toast.makeText(requireContext(), "arg1 = ${safeArgs.myArgument}   arg2 = ${safeArgs.argument2}", Toast.LENGTH_SHORT).show()

        bindin.save.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote(){
//        val title = bindin.titleInput.text.toString()
//        val note = bindin.noteInput.text.toString()
//        val bundle = bundleOf(
//            TITLE to title,
//            NOTE to note
//        )
//        parentFragmentManager.setFragmentResult(NOTE_RESULT, bundle)
//        parentFragmentManager.popBackStack()
        findNavController().navigate(R.id.action_addNote_to_details)
    }


    companion object {
        const val NOTE_RESULT = "noteResult"
        const val TITLE = "title"
        const val NOTE = "note"
    }

}