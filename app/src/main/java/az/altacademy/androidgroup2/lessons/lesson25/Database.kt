package az.altacademy.androidgroup2.lessons.lesson25

import android.content.Context
import androidx.room.Room

object Database {

    private var database: AppDatabase? = null

    fun initDatabase(context: Context) {
        database = Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "app-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    fun getPersonDAO(): PersonDao? {
        return database?.getPersonDAO()
    }

    fun getStudentDao(): StudentDao? {
        return database?.getStudentDAO()
    }
}