package az.altacademy.androidgroup2.note.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentNoteRegisterBinding
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import az.altacademy.androidgroup2.note.ui.login.NoteLoginVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteRegisterFragment : Fragment() {

    private lateinit var binding: FragmentNoteRegisterBinding
    private val viewModel by viewModels<NoteRegisterVM>()
    private val loginVM by viewModels<NoteLoginVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            onRegisterClick()
        }

        binding.buttonGoToLogin.setOnClickListener {
            findNavController().navigate(
                NoteRegisterFragmentDirections.actionNoteRegisterToNoteLogin()
            )
        }

        viewModel.state.observe(viewLifecycleOwner){state ->
            onStateChange(state)
        }

        loginVM.state.observe(viewLifecycleOwner){state ->
            onLoginStateChange(state)
        }
    }

    private fun onStateChange(state: CoreUIState<NoteRegisterVM.State>){
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

    private fun onLoginStateChange(state: CoreUIState<NoteLoginVM.State>){
        when(state){
            is CoreUIState.Success ->{
                onLoginSuccess(state.data)
            }
            is CoreUIState.Loading ->{}
            is CoreUIState.Error ->{
                Toast.makeText(requireContext(), "${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onLoginSuccess(data: NoteLoginVM.State) {
        if (data.isLoggedIn){
            findNavController().navigate(NoteRegisterFragmentDirections.actionNoteRegisterToNoteList())
        }
    }


    private fun onSuccess(state: NoteRegisterVM.State){
        if (state.isRegistered){
            loginVM.loginUser(state.email.orEmpty(), state.password.orEmpty())
        }
        showFieldError(state.isValidMail, state.isValidPassword, state.isValidRepeatPassword)
    }

    private fun showFieldError(isValidEmail: Boolean, isValidPass: Boolean, isValidRePass: Boolean){
        if (!isValidEmail)
            binding.emailInputLayout.error = "Email duzgun deyil"

        if (!isValidPass)
            binding.passwordInputLayout.error = "Password duzgun deyil"

        if (!isValidRePass)
            binding.repeatPasswordInputLayout.error = "Password-lar uygun deyil"
    }

    private fun onRegisterClick() {
        binding.emailInputLayout.error = null
        binding.passwordInputLayout.error = null
        binding.repeatPasswordInputLayout.error = null
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()
        val repeatPassword = binding.repeatPasswordInput.text.toString()
        viewModel.registerUser(email, password, repeatPassword)
    }

}