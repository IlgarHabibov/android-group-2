package az.altacademy.androidgroup2.lessons.lesson32.di

import az.altacademy.androidgroup2.lessons.lesson27.ApiService
import az.altacademy.androidgroup2.lessons.lesson27.PostApiService
import az.altacademy.androidgroup2.lessons.weather.WeatherApiKeyInterceptor
import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService{
        return retrofit.create(WeatherApiService::class.java)
    }


    @Provides
    fun providePostApiService(retrofit: Retrofit): PostApiService{
        return retrofit.create(PostApiService::class.java)
    }


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}