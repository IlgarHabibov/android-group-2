package az.altacademy.androidgroup2.note.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentNoteListBinding
import az.altacademy.androidgroup2.databinding.FragmentNoteLoginBinding
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.register.NoteRegisterVM
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteLoginFragment : Fragment() {


    private lateinit var binding: FragmentNoteLoginBinding
    private val viewModel by viewModels<NoteLoginVM>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackToRegister.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonLogin.setOnClickListener {
            onLoginClick()
        }

        viewModel.state.observe(viewLifecycleOwner){state ->
            onStateChange(state)
        }
    }

    private fun onStateChange(state: CoreUIState<NoteLoginVM.State>){
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

    private fun onSuccess(state: NoteLoginVM.State){
        if (state.isLoggedIn){
            findNavController().navigate(
                NoteLoginFragmentDirections.actionNoteLoginToNoteList()
            )
        }
        showFieldError(state.isValidMail, state.isValidPassword)

    }

    private fun showFieldError(isValidEmail: Boolean, isValidPass: Boolean){
        if (!isValidEmail)
            binding.emailInputLayout.error = "Email duzgun deyil"

        if (!isValidPass)
            binding.passwordInputLayout.error = "Password duzgun deyil"

    }

    private fun onLoginClick() {
        binding.emailInputLayout.error = null
        binding.passwordInputLayout.error = null
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()
        viewModel.loginUser(email, password)

    }

}