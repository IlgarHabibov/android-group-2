package az.altacademy.androidgroup2.lessons.lesson32.di

import android.content.Context
import androidx.room.Room
import az.altacademy.androidgroup2.lessons.lesson25.AppDatabase
import az.altacademy.androidgroup2.lessons.lesson25.PersonDao
import az.altacademy.androidgroup2.lessons.lesson25.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun providePersonDao(appDatabase: AppDatabase): PersonDao{
        return appDatabase.getPersonDAO()
    }

    @Provides
    fun provideStudentDao(appDatabase: AppDatabase): StudentDao{
        return appDatabase.getStudentDAO()
    }
}