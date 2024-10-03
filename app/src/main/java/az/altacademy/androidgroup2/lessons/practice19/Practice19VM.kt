package az.altacademy.androidgroup2.lessons.practice19

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Practice19VM @Inject constructor(
    private val university: University
) : ViewModel() {


    var state = MutableLiveData<String>()

    fun getUniversityInfo(): University {
        return university
    }

    fun startFlow() {
        viewModelScope.launch {
//            flowOf("Start", "Running", "Stop")
//                .collect{ element ->
//                    state.value = element
//                    delay(2000)
//                }

//            val list = listOf("Start", "Running", "Stop")
//            list.asFlow().collect{
//                    element ->
//                state.value = element
//                delay(2000)
//            }

            getFlow()
                .filter { element -> element.first() == 'S' }
                .map { element -> "- $element -" }
                .transform { element ->
                    emit("* $element *")
                    emit("+ $element +")
                    emit("$ $element $")
                }
                .take(3)
                .flowOn(Dispatchers.Default)
                .onStart {
                    state.value = "Starting"
                    delay(1000)
                }
                .collect { element ->
                    state.value = element
                    delay(2000)
                }

//            val numbers = flowOf(1, 2, 3, 4)
//            val chars = flowOf("a", "b", "c", "d")
//
//            numbers.zip(chars){ number, char ->
//                "$number - $char"
//            }.collect{element ->
//                state.value = element
//                delay(2000)
//            }

        }
    }

    private fun getFlow() = flow<String> {
        emit("Start")
        emit("Running")
        emit("Stop")

    }
}

data class Status(val status: String)
data class NewStatus(val status: String)