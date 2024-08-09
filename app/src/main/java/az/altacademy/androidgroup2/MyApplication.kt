package az.altacademy.androidgroup2

import android.app.Application
import androidx.room.Room
import az.altacademy.androidgroup2.lessons.lesson25.AppDatabase

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
         Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "my_database"
        )
            .allowMainThreadQueries()
            .build()
    }
}