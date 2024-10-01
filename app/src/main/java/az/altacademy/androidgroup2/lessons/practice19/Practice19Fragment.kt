package az.altacademy.androidgroup2.lessons.practice19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Practice19Fragment : Fragment() {

    private val viewModel by viewModels<Practice19VM> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice19, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val university =  viewModel.getUniversityInfo()
        university.printStatus()
    }

}