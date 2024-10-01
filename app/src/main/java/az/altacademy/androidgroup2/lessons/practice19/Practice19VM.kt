package az.altacademy.androidgroup2.lessons.practice19

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Practice19VM @Inject constructor (
    private val university: University
): ViewModel() {

    fun getUniversityInfo(): University{
        return university
    }
}