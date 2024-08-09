package az.altacademy.androidgroup2.lessons.lesson25

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "student_table",
)
data class StudentEntity(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo("student_id")
    var id: Int ,

    var name: String,
    var surname: String,
    var age: Int,
    var address: String
)
