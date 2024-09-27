package az.altacademy.androidgroup2

import android.app.Application
import az.altacademy.androidgroup2.lessons.lesson25.Database
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}