package az.altacademy.androidgroup2.lessons.weather

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class WeatherApiKeyInterceptor @Inject constructor(): Interceptor {

    private val key = "4765cd99e89445428c9165252240609"
    private val lang = "en"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val newUrl = "${chain.request().url}&lang=$lang&key=$key"
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}