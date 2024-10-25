package az.altacademy.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import az.altacademy.first.databinding.FragmentModuleTestBinding
import az.altacademy.second.Academy

class ModuleTestFragment : Fragment() {

    private lateinit var binding: FragmentModuleTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModuleTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val academy = Academy(name = "ATL", address = "Baku")

        Toast.makeText(requireContext(), "name=${academy.name}  address=${academy.address}", Toast.LENGTH_SHORT).show()
    }

}