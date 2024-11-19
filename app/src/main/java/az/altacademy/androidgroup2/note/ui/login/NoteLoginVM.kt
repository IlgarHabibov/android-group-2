package az.altacademy.androidgroup2.note.ui.login

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
class NoteLoginVM @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state: MutableLiveData<CoreUIState<State>> = MutableLiveData()
    val state: LiveData<CoreUIState<State>> = _state


    fun loginUser(email: String, password: String) {
        val isValidEmail = checkEmail(email)
        val isValidPassword = checkPassword(password)
        if (isValidEmail && isValidPassword) {
            loginWithFirebase(email, password)
        } else {
            _state.value = CoreUIState.Success(
                State(
                    isLoggedIn = false,
                    isValidMail = isValidEmail,
                    isValidPassword = isValidPassword,
                )
            )
        }
    }

    private fun loginWithFirebase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseEmailTag", "loginWithFirebase: Success")
                    _state.value = CoreUIState.Success(State(
                        isLoggedIn = true
                    ))
                } else {

                }
            }
            .addOnFailureListener { error ->
                _state.value = CoreUIState.Error(200, error.localizedMessage)
            }
    }


    private fun checkEmail(email: String): Boolean {
        return email.length >= 6
    }

    private fun checkPassword(password: String): Boolean {
        return password.length >= 8
    }



    data class State(
        val isLoggedIn: Boolean,
        val isValidMail: Boolean = true,
        val isValidPassword: Boolean = true
    )

}