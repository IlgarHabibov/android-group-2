package az.altacademy.androidgroup2.lessons.lesson28_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentCreatePostBinding


class CreatePostFragment : Fragment() {

    private lateinit var binding: FragmentCreatePostBinding
    private val viewModel by viewModels<CreatePostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createPostButton.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val desc = binding.inputBody.text.toString()
            createPost(title, desc)
        }

        viewModel.state.observe(viewLifecycleOwner){ post ->

        }

        viewModel.error.observe(viewLifecycleOwner){ post ->

        }
    }

    private fun createPost(title: String, desc: String){
        viewModel.createPost(title, desc)
    }

}