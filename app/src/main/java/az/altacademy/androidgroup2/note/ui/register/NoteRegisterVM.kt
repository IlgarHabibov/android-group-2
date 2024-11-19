package az.altacademy.androidgroup2.note.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import az.altacademy.androidgroup2.lessons.weather.UIState
import az.altacademy.androidgroup2.note.ui.core.CoreUIState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteRegisterVM @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state: MutableLiveData<CoreUIState<State>> = MutableLiveData()
    val state: LiveData<CoreUIState<State>> = _state


    fun registerUser(email: String, password: String, repeatPassword: String) {
        val isValidEmail = checkEmail(email)
        val isValidPassword = checkPassword(password)
        val isValidRepeatPassword = checkRepeatPassword(password, repeatPassword)
        if (isValidEmail && isValidPassword && isValidRepeatPassword) {
            registerWithFirebase(email, password)
        } else {
            _state.value = CoreUIState.Success(
                State(
                    isRegistered = false,
                    isValidMail = isValidEmail,
                    isValidPassword = isValidPassword,
                    isValidRepeatPassword = isValidRepeatPassword
                )
            )
        }
    }

    private fun registerWithFirebase(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseEmailTag", "registerWithFirebase: Success")
                    _state.value = CoreUIState.Success(State(
                        isRegistered = true,
                        email = email,
                        password = password
                    ))
                } else {

                }
            }
            .addOnFailureListener { error ->
                _state.value = CoreUIState.Error(100, error.localizedMessage)
            }
    }

    private fun loginWithFirebase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.value = CoreUIState.Success(State(
                        isRegistered = true,
                        email = email,
                        password = password
                    ))
                } else {

                }
            }
            .addOnFailureListener { error ->
                _state.value = CoreUIState.Error(100, error.localizedMessage)
            }
    }


    private fun checkEmail(email: String): Boolean {
        return email.length >= 6
    }

    private fun checkPassword(password: String): Boolean {
        return password.length >= 8
    }

    private fun checkRepeatPassword(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword && repeatPassword.isNotEmpty()
    }


    data class State(
        val isRegistered: Boolean,
        val isValidMail: Boolean = true,
        val isValidPassword: Boolean = true,
        val isValidRepeatPassword: Boolean = true,
        val email: String? = null,
        val password: String? = null
    )

}