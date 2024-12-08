package az.altacademy.androidgroup2.news.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.news.data.datasource.NewsDataSource
import az.altacademy.androidgroup2.news.data.models.NewsModel
import az.altacademy.androidgroup2.utils.apiCall
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val newsDataSource: NewsDataSource
): PagingSource<Int, NewsModel>() {

    private var isFinished = false
    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        val page = params.key ?: 1

        if (isFinished){
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
        val result = apiCall { newsDataSource.getNews(page = page, pageSize = params.loadSize) }
        val nextKey = page + 1

        return when(result){
            is ApiState.Success -> {
                isFinished = result.data?.articles.isNullOrEmpty()
                LoadResult.Page(
                    data = result.data?.articles.orEmpty(),
                    prevKey = null,
                    nextKey = nextKey
                )
            }
            is ApiState.Error -> {
                LoadResult.Error(Throwable(result.error?.message))
            }
        }
    }
}