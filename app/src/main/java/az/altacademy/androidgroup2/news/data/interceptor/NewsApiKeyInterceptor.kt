package az.altacademy.androidgroup2.news.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsApiKeyInterceptor @Inject constructor(): Interceptor {

    private val key = "7e0a156830164ca395f8dc9237e8d956"
    private val lang = "en"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val newUrl = "${chain.request().url}&apiKey=$key"
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}