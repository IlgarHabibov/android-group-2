package az.altacademy.androidgroup2.lessons.lesson28_2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostViewModel: ViewModel() {

    var state = MutableLiveData<CreatePostResponse>()
    var error = MutableLiveData<String>()

    fun createPost(title: String, desc: String){
        val request = CreatePostModel(
            title = title,
            body = desc,
            userId = 10
        )
        ApiManager.getPostApiService().createPost(request).enqueue(object:  Callback<CreatePostResponse>{

            override fun onResponse(call: Call<CreatePostResponse>, response: Response<CreatePostResponse>) {
                Log.d("asdasdasdasdasd", "onResponse: ${response.body()}")
                state.value = response.body()
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                error.value = t.localizedMessage
            }

        })
    }
}