package az.altacademy.androidgroup2.lessons.lesson27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.FragmentLesson272Binding
import az.altacademy.androidgroup2.databinding.FragmentLesson27Binding
import coil.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Lesson272Fragment : Fragment() {

    private lateinit var binding: FragmentLesson272Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesson272Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = Lesson272FragmentArgs.fromBundle(arguments ?: bundleOf())
        val id = args.id
        Log.d("asdASdasdasdasd", "id: $id")


        ApiManager.getApiService().getFactById(id).enqueue(object : Callback<FactResponse> {

            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                Log.d("asdASdasdasdasd", "onResponse: $response")
                val data = response.body()
                onSuccess(data)
            }

            override fun onFailure(call: Call<FactResponse>, throwable: Throwable) {
            }


        })
    }

    private fun onSuccess(data: FactResponse?){
        binding.image.load(data?.user?.photo)
        binding.name.text = "${data?.user?.name?.first}  ${data?.user?.name?.last}"
        binding.text.text = data?.text
    }


}