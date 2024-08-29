package az.altacademy.androidgroup2.lessons.lesson26

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import az.altacademy.androidgroup2.lessons.lesson25.AppDatabase
import az.altacademy.androidgroup2.lessons.lesson25.PersonEntity

class MyFirstViewModel : ViewModel() {

    private var db: AppDatabase? = null

    fun savePerson(name: String, surname: String) {
        val person = PersonEntity(name = name, surname = surname)
        db?.getPersonDAO()?.addPerson(person)
    }

    fun delete(personEntity: PersonEntity){
        db?.getPersonDAO()?.deletePerson(personEntity)
    }

    fun getAllPersons(): LiveData<List<PersonEntity>>?{
        val liveData = db?.getPersonDAO()?.getAllPersons()
        return liveData
    }

    fun createDb(context: Context) {
        db = Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "app-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    fun destroyDB(){
        db = null
    }
}