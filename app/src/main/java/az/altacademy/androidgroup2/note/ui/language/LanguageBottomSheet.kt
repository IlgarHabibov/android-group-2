package az.altacademy.androidgroup2.note.ui.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.BottomSheetLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguageBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = BottomSheetLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.labelAz.setOnClickListener {
            setFragmentResult("language", bundleOf("lang" to "az"))
            dismiss()
        }

        binding.labelEn.setOnClickListener {
            setFragmentResult("language", bundleOf("lang" to "en"))
            dismiss()
        }
    }

}