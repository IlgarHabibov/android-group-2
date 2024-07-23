package az.altacademy.androidgroup2.homework19

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentSocialBinding


class SocialFragment : Fragment() {

    private var binding: FragmentSocialBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val social = arguments?.getString("social")
        val url = arguments?.getString("url")

        binding?.socialText?.text = social

        binding?.socialText?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            requireActivity().startActivity(intent)
        }

        binding?.backButton?.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                "HideButtons",
                bundleOf(
                    "text" to social
                )
            )
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}