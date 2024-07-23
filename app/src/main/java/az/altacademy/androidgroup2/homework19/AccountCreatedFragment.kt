package az.altacademy.androidgroup2.homework19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentAccountCreatedBinding
import az.altacademy.androidgroup2.databinding.FragmentSocialBinding


class AccountCreatedFragment : Fragment() {
    private var binding: FragmentAccountCreatedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountCreatedBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString("email")
        val password = arguments?.getString("password")

        binding?.accountInfoText?.text = "Email:   $email \nPassword:   $password "

        binding?.backButton?.setOnClickListener {
            parentFragmentManager.setFragmentResult("HideGoogle", bundleOf())
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}