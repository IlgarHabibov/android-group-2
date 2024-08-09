package az.altacademy.androidgroup2

import android.app.Application
import az.altacademy.androidgroup2.lessons.lesson25.Database

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Database.initDatabase(this)
    }
}