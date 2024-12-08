package az.altacademy.androidgroup2.news.data.datasource

import az.altacademy.androidgroup2.news.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("everything?q=bitcoin")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<NewsResponse>
}