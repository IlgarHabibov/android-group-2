package az.altacademy.androidgroup2.lessons.lesson25

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface StudentDao {
    @Insert
    fun addStudentInfo(student: StudentEntity)

    @Update
    fun changeStudentInfo(student: StudentEntity)

    @Upsert
    fun addOrChangeStudentInfo(student: StudentEntity)

    @Delete
    fun deleteStudentInfo(student: StudentEntity)

    @Query("SELECT * FROM student_table")
    fun getAllStudentsInfo(): List<StudentEntity>

    @Query("SELECT name FROM student_table")
    fun getStudentsNames(): List<String>

    @Query("SELECT age FROM student_table WHERE age > 10")
    fun getStudentsAges(): List<Int>
}