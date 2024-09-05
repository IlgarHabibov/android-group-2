package az.altacademy.androidgroup2.lessons.lesson27

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Lesson27VM: ViewModel() {

    var state : MutableLiveData<List<FactsResponse>?> = MutableLiveData<List<FactsResponse>?>()
    var x : MutableLiveData<String> = MutableLiveData<String>()

    fun getFacts(){
        val request =  ApiManager.getApiService().getFacts().request()


        ApiManager.getApiService().getFacts().enqueue(object : Callback<List<FactsResponse>> {

            override fun onResponse(call: Call<List<FactsResponse>>, response: Response<List<FactsResponse>>) {
                val data = response.body()
                state.value = data
            }

            override fun onFailure(call: Call<List<FactsResponse>>, throwable: Throwable) {
                x.value = throwable.localizedMessage
            }

        })
    }
}