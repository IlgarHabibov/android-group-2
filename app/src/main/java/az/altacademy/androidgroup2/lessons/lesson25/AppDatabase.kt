package az.altacademy.androidgroup2.lessons.lesson25

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonEntity::class, StudentEntity::class],
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPersonDAO(): PersonDao
    abstract fun getStudentDAO(): StudentDao
}