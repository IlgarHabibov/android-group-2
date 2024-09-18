package az.altacademy.androidgroup2.lessons.lesson28_2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.lessons.weather.ApiResult
import az.altacademy.androidgroup2.utils.apiCall
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostViewModel: ViewModel() {

    var state = MutableLiveData<CreatePostResponse?>()
    var error = MutableLiveData<String>()

    fun createPost(title: String, desc: String){
        val request = CreatePostModel(
            title = title,
            body = desc,
            userId = 10
        )
        viewModelScope.launch {
            val result = apiCall { ApiManager.getPostApiService().createPost(request) }
            when(result){
                is ApiResult.Success -> {
                    state.value = result.data
                }
                is ApiResult.Error -> {
                    error.value = result.error?.errorDescription.toString()
                }
            }
        }
    }
}