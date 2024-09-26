package az.altacademy.androidgroup2.lessons.lesson31

import javax.inject.Inject

class Car @Inject constructor (private val engine: Engine) {

    fun startEngine(){
        engine.startEngine()
    }
}

class Engine @Inject constructor (private val detail: Detail) {

    fun startEngine(){
        println(" id=${hashCode()} detailId=${detail.hashCode()} started" )
    }
}

class Detail @Inject constructor ()
