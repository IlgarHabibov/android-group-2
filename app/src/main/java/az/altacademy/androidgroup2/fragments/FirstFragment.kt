package az.altacademy.androidgroup2.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import az.altacademy.androidgroup2.MainActivity
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addNote?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment())
                .addToBackStack(FirstFragment::class.simpleName)
                .commit()
        }

        parentFragmentManager.setFragmentResultListener(
            "NoteResult",
            viewLifecycleOwner
        ){ resultKey, bundle ->

            if (resultKey == "NoteResult"){
                val text = bundle.getString("note")
                binding?.fragmentLabel?.text = text
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}