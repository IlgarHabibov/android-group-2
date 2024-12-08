package az.altacademy.androidgroup2.news.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.altacademy.androidgroup2.news.data.models.NewsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsPagingRepository @Inject constructor (
    private val pagingSource: NewsPagingSource
) {
    fun getPagedNews(): Flow<PagingData<NewsModel>> {
        val pagingConfig = PagingConfig(pageSize = 10, initialLoadSize = 10)
        val pager = Pager(
            config = pagingConfig,
            pagingSourceFactory = {pagingSource}
        )
        return pager.flow
    }
}