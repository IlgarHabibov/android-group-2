package az.altacademy.androidgroup2.lessons.lesson25

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface PersonDao {

    @Insert
    fun addPerson(person: PersonEntity)

    @Update
    fun updatePerson(person: PersonEntity)

    @Upsert
    fun updateAndInsertPerson(person: PersonEntity)

    @Delete
    fun deletePerson(person: PersonEntity)

    @Query("SELECT * FROM personentity")
    fun getAllPersons(): LiveData<List<PersonEntity>>

    @Query("SELECT * FROM personentity WHERE id = :personId")
    fun getPersonById(personId: Int): PersonEntity


}