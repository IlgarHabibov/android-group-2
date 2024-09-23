package az.altacademy.androidgroup2.lessons.lesson28_2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.altacademy.androidgroup2.lessons.lesson27.ApiManager
import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.utils.apiCall
import kotlinx.coroutines.launch

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
                is ApiState.Success -> {
                    state.value = result.data
                }
                is ApiState.Error -> {
//                    error.value = result.error?.errorDescription.toString()
                }
            }
        }
    }
}