package az.altacademy.androidgroup2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.saveNote?.setOnClickListener {
            val text = binding?.noteInput?.text?.toString()

            val bundle = Bundle()
            bundle.putString("note", text)

//            val bundle2 = bundleOf(
//                "note" to text
//            )
//
            parentFragmentManager.setFragmentResult("NoteResult", bundle)
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}