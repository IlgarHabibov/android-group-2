package az.altacademy.androidgroup2.lessons.lesson29

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.header("Lang", "Az")
        return chain.proceed(request.build())
    }
}