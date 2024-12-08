package az.altacademy.androidgroup2.news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.CurrentWeatherResponse
import az.altacademy.androidgroup2.lessons.weather.UIState
import az.altacademy.androidgroup2.news.data.datasource.NewsDataSource
import az.altacademy.androidgroup2.news.data.models.NewsModel
import az.altacademy.androidgroup2.news.data.models.NewsResponse
import az.altacademy.androidgroup2.utils.apiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsVM @Inject constructor(
    private val newsPagingSource: NewsPagingSource
): ViewModel() {

    private val _state: MutableLiveData<UIState<PagingData<NewsModel>>> = MutableLiveData<UIState<PagingData<NewsModel>>>()
    val state: LiveData<UIState<PagingData<NewsModel>>> = _state


    fun getNewsData() {
        viewModelScope.launch {
            val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
            val pager = Pager(
                config = pagingConfig,
                pagingSourceFactory = {newsPagingSource}
            )
            pager.flow.collectLatest {
                _state.value = UIState.Success(it)
            }

        }
    }
}