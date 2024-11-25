package az.altacademy.androidgroup2.note.ui.notelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentNoteListBinding
import az.altacademy.androidgroup2.databinding.FragmentNoteRegisterBinding
import az.altacademy.androidgroup2.note.ui.ThemeVM
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.register.NoteRegisterVM
import az.altacademy.androidgroup2.utils.LanguageHelper
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel by viewModels<NoteListVM>()
    private val themeVM by activityViewModels<ThemeVM>()
    private val adapter = NotesListAdapter()


    @Inject
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newNotesRecycler.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            onStateChange(state)
        }
        firebaseAuth.currentUser?.uid?.let {
            viewModel.getUserNotes(it)
        }

        binding.addNewNote.setOnClickListener {
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListToAddNewNote()
            )
        }

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListToNoteRegister()
            )
        }


        binding.changeTheme.setOnClickListener {
            themeVM.changeTheme()
        }

        themeVM.theme.observe(viewLifecycleOwner) { isDark ->
            val iconResId = if (isDark == true) {
                R.drawable.ic_dark_mode
            } else {
                R.drawable.ic_light_mode
            }
            binding.changeTheme.setImageResource(iconResId)
        }

        binding.changeLanguage.setOnClickListener {
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToLanguageBottomSheet()
            )
        }

        setFragmentResultListener("language") { key, bundle ->
            val language = bundle.getString("lang")
            when (language) {
                "az" -> {
                    changeLang("az")
                }

                "en" -> {
                    changeLang("en")
                }
            }
        }

    }

    private fun changeLang(lang: String) {
        val newContext = LanguageHelper.setLocale(requireContext(), lang)
        requireActivity().recreate()
    }

    private fun onStateChange(state: CoreUIState<NoteListVM.State>) {
        when (state) {
            is CoreUIState.Success -> {
                onSuccess(state.data)
            }

            is CoreUIState.Loading -> {}
            is CoreUIState.Error -> {
                Toast.makeText(requireContext(), "${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onSuccess(data: NoteListVM.State) {
        adapter.addData(data.noteList)
    }

}