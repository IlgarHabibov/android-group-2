package az.altacademy.androidgroup2.lessons.lesson27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentLesson27Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Lesson27Fragment : Fragment() {

    private lateinit var binding: FragmentLesson27Binding
    private val adapter = Lesson27Adapter()
    private val viewModel by viewModels<Lesson27VM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesson27Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lesson27Recycler.adapter = adapter

        adapter.setOnItemClick { id ->
            findNavController().navigate(
                Lesson27FragmentDirections.actionLesson27FragmentToLesson272Fragment(id)
            )
        }
        viewModel.getFacts()

        viewModel.state.observe(viewLifecycleOwner){ list ->
            adapter.addData(list.orEmpty())
        }

        viewModel.x.observe(viewLifecycleOwner){ error ->
            Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_SHORT).show()
        }


//        ApiManager.getApiService().getFacts().enqueue(object : Callback<List<FactsResponse>>{
//
//            override fun onResponse(
//                call: Call<List<FactsResponse>>,
//                response: Response<List<FactsResponse>>
//            ) {
//
//                adapter.addData(response.body().orEmpty())
//            }
//
//            override fun onFailure(call: Call<List<FactsResponse>>, throwable: Throwable) {
//                Log.d("asdasdasdasdasd", "onFailure: ${throwable.localizedMessage}")
//                Toast.makeText(requireContext(), "Error: ${throwable.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }
//
//        })

    }

}