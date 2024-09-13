package az.altacademy.androidgroup2.lessons.lesson27

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import az.altacademy.androidgroup2.R
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class Lesson27Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson27)

        lifecycleScope.launch {
            delay(1000)
            printSimpleText("1")
            val name = async {
                getNameFromApi()
            }

            val surname = async {
                getSurnameFromApi()
            }
            printSimpleText("name = ${name.await()} surname = ${surname.await()}")
            printCoroutineText("basqa nese")
        }
        printSimpleText("text 4")

        lifecycleScope.launch {
            count(60)
        }
        printSimpleText("")
        testFunction()
    }

    private fun dispatchersText() {
        lifecycleScope.launch {
            printSimpleText("working UI")
            withContext(Dispatchers.IO) {
                printSimpleText("Api call")
                val result = "Done"
                withContext(Dispatchers.Default) {
                    printSimpleText("sorting result")
                    withContext(Dispatchers.Main) {
                        printSimpleText("Livedata value changed")
                    }
                }
            }
        }
    }


    private fun jobs() {
        val button = Button(this)
        lifecycleScope.launch {
            val job = launch {
                printSimpleText("Started")
                delay(10000)
                printSimpleText("Finished")
            }

            button.setOnClickListener {
                job.cancel()
            }
        }
    }


    private suspend fun count(seconds: Int) {
        for (i in 0..seconds) {
            printSimpleText(i.toString())
            delay(1000)
            printCoroutineText("-------------------")
        }
    }


    private suspend fun printCoroutineText(text: String) {
        Log.d("Lesson27ActivityTAG", "$text")
    }

    private fun printSimpleText(text: String) {
        Log.d("Lesson27ActivityTAG", "SimpleText = $text ")
    }


    private suspend fun getNameFromApi(): String {
        delay(3000)
        return "Ilgar"
    }

    private suspend fun getSurnameFromApi(): String {
        delay(5000)
        return "Habibov"
    }


    private fun testFunction() {
        lifecycleScope.launch {
            delay(1000)
            launch {
                printSimpleText("a")
                delay(1000)
                printSimpleText("1")
            }

            launch {
                printSimpleText("b")
                launch {
                    printSimpleText("d")
                    delay(2000)
                    printSimpleText("4")
                }
                printSimpleText("2")
            }

            launch {
                printSimpleText("c")
                delay(500)
                printSimpleText("3")
            }
        }
    }
}