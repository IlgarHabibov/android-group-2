package az.altacademy.androidgroup2.lessons.lesson32.di

import az.altacademy.androidgroup2.lessons.lesson31.Detail
import az.altacademy.androidgroup2.lessons.lesson31.Engine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CarModule {

//    @Named("power200")
//    @Provides
//    fun providePower200(): Int{
//        return 200
//    }
//
//    @Named("power150")
//    @Provides
//    fun providePower150(): Int{
//        return 150
//    }
//
//    @Engine150
//    @Provides
//    fun provideEngine150(@Named("power150") power: Int, detail: Detail): Engine {
//        return Engine(power, detail)
//    }
//
//    @Engine200
//    @Provides
//    fun provideEngine200(@Named("power200") power: Int, detail: Detail): Engine {
//        return Engine(power, detail)
//    }


    @Provides
    fun providePower150(): Int{
        return 150
    }

    @Provides
    fun provideEngine150(power: Int, detail: Detail): Engine {
        return Engine(power, detail)
    }

}