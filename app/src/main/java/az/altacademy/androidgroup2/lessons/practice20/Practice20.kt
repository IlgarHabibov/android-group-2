package az.altacademy.androidgroup2.lessons.practice20

import kotlinx.coroutines.flow.flowOf

fun main() {

    val flow = flowOf("value 1", "value 2", "value 3")

    /* Collect
        value 1.1
        value 1.2
        value 1.3
        value 2.1
        value 2.2
        value 2.3
        value 3.1
        value 3.2
        value 3.3
     */
}