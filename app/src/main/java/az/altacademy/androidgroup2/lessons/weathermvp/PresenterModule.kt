package az.altacademy.androidgroup2.lessons.weathermvp

import az.altacademy.androidgroup2.lessons.weather.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresenterModule {

    @Provides
    fun provideWeatherPresenter(
        weatherRepository: WeatherRepository
    ): WeatherPresenter{
        return WeatherPresenterImpl(weatherRepository)
    }
}