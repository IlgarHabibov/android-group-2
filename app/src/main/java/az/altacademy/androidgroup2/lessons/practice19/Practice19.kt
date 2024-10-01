package az.altacademy.androidgroup2.lessons.practice19

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class Address @Inject constructor (
    var region: String
)

class Student @Inject constructor (
    @StudentAddress var address: Address
)

class Teacher @Inject constructor(
    @TeacherAddress var address: Address
)

class University @Inject constructor(
    @UniversityAddress private var address: Address,
    private var student: Student,
    private var teacher: Teacher
){

    fun printStatus(){
        Log.d("UniversityTAG", "status == Success: ")
        Log.d("UniversityTAG", "universityAddress=${address.region}")
        Log.d("UniversityTAG", "teacherAddress=${teacher.address.region}")
        Log.d("UniversityTAG", "studentAddress=${student.address.region}")
    }
}
