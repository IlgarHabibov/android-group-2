package az.altacademy.androidgroup2.lessons.lesson31

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HiltViewmodel @Inject constructor (
    private val car: Car
): ViewModel() {

    fun startCar(){
        car.startEngine()
    }
}