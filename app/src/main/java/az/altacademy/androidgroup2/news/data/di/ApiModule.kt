package az.altacademy.androidgroup2.news.data.di

import az.altacademy.androidgroup2.lessons.weather.WeatherApiKeyInterceptor
import az.altacademy.androidgroup2.news.data.datasource.NewsDataSource
import az.altacademy.androidgroup2.news.data.interceptor.NewsApiKeyInterceptor
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
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Named("News")
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        val level = HttpLoggingInterceptor.Level.BODY
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = level
        return interceptor
    }

    @Named("News")
    @Provides
    fun provideOkhttpClient(
        newsApiKeyInterceptor: NewsApiKeyInterceptor,
        @Named("News") httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(newsApiKeyInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Named("News")
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Named("News")
    @Provides
    fun provideRetrofit(
        @Named("News") okHttpClient: OkHttpClient,
        @Named("News") gsonConverterFactory: GsonConverterFactory
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideNewsDataSource(@Named("News") retrofit: Retrofit): NewsDataSource{
        return retrofit.create(NewsDataSource::class.java)
    }


}