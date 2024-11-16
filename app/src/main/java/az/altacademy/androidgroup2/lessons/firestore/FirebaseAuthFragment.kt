package az.altacademy.androidgroup2.lessons.firestore

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentFirebaseAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.MultiFactorSession
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.messaging.FirebaseMessaging
import java.util.concurrent.TimeUnit

class FirebaseAuthFragment : Fragment() {

    private lateinit var binding: FragmentFirebaseAuthBinding

    private val pushPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirebaseAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pushPermission.launch(Manifest.permission.POST_NOTIFICATIONS)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("FirebaseMessagingTag", "token2: ${it.result}")

        }

        binding.phoneLoginButton.setOnClickListener {
            loginWithPhone("+994508766516")
        }
    }

    private fun loginWithPhone(phone: String){

        val calback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("FireBaseAuthTag", "Success ${p0.smsCode}")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("FireBaseAuthTag", "Fail ${p0.localizedMessage}")
            }

        }

        val phoneAuthOptions = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phone)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setCallbacks(calback)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }


    private fun loginWithGoogle(){

//        val signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(getString(R.string.your_web_client_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
    }


}