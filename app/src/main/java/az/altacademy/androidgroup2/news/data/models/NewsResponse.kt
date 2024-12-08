package az.altacademy.androidgroup2.news.data.models

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsModel>
)
