package az.altacademy.androidgroup2.lessons.architecture

open class Bird{
    open fun eat(): String{
        return ""
    }
}

interface Flyable{
    fun fly(): String
}

class Penguin: Bird(){

    override fun eat(): String {
        return "Penguin eating"
    }
}

class Owl(): Bird(), Flyable{

    override fun fly(): String {
        return "Owl flying"
    }

    override fun eat(): String {
        return "Owl eating"
    }
}