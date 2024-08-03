package az.altacademy.androidgroup2.homework19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentCreateAccountBinding
import az.altacademy.androidgroup2.databinding.FragmentSocialBinding


class CreateAccountFragment : Fragment() {
    private var binding: FragmentCreateAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        parentFragmentManager.setFragmentResultListener(
            "HideButtons",
            viewLifecycleOwner
        ){ key, bundle ->
            binding?.googleButton?.isVisible = false
            binding?.appleButton?.isVisible = false
            binding?.facebookButton?.isVisible = false
            val text = bundle.getString("text")
            Toast.makeText(requireContext(), "text = $text", Toast.LENGTH_SHORT).show()
        }

        parentFragmentManager.setFragmentResultListener(
            "HideGoogle",
            viewLifecycleOwner
        ){ key, bundle ->
            binding?.googleButton?.isVisible = false
        }


        binding?.createButton?.setOnClickListener {
            val email = binding?.emailInput?.text.toString()
            val password = binding?.passwordInput?.text.toString()
            val bundle = bundleOf(
                "email" to email,
                "password" to password
            )
            findNavController().navigate(R.id.action_createAccount_to_accountCreated, bundle)
        }


        binding?.googleButton?.setOnClickListener {
            val bundle = bundleOf(
                "social" to "Google",
                "url" to "https://www.google.ru/"
            )
            changeFragment(SocialFragment::class.java, bundle)
        }

        binding?.appleButton?.setOnClickListener {
            val bundle = bundleOf(
                "social" to "Apple",
                "url" to "https://www.apple.com/"
            )
            changeFragment(SocialFragment::class.java, bundle)
        }


        binding?.facebookButton?.setOnClickListener {
            val bundle = bundleOf(
                "social" to "Facebook",
                "url" to "https://www.facebook.com/"
            )
            changeFragment(SocialFragment::class.java, bundle)
        }
    }

    private fun changeFragment(fragment: Class<out Fragment>, bundle: Bundle? = null){
        parentFragmentManager.beginTransaction()
            .replace(R.id.homework_19_container, fragment, bundle)
            .addToBackStack(fragment.canonicalName)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}