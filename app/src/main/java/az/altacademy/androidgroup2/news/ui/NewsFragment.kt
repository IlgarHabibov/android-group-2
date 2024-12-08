package az.altacademy.androidgroup2.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.BottomSheetLanguageBinding
import az.altacademy.androidgroup2.databinding.FragmentNewsBinding
import az.altacademy.androidgroup2.lessons.weather.UIState
import az.altacademy.androidgroup2.news.data.models.NewsModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModels<NewsVM>()
    private val adapter = NewsAdapter()
    private val loadStateAdapter = LoadStateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNewsData()
        binding.newsRecycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)

        viewModel.state.observe(viewLifecycleOwner){
                state ->
            when (state) {
                is UIState.Success -> {
                    setDataToAdapter(state.data)
                }
                is UIState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "${state.errorCode} : ${state.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UIState.Loading -> {
                    binding.loading.isVisible = state.isLoading
                }
            }
        }
    }

    private fun setDataToAdapter(data: PagingData<NewsModel>) {
        lifecycleScope.launch {
            adapter.submitData(data)
        }
    }


}