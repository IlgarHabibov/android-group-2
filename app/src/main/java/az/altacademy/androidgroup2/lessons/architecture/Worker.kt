package az.altacademy.androidgroup2.lessons.architecture

interface Worker{
    fun startWorking(): String
}

interface Programming{
    fun codding(): String
}

interface Designing{
    fun designing(): String
}

class Programmer: Worker, Programming{

    override fun codding(): String {
        return "writing code"
    }

    override fun startWorking(): String {
        return codding()
    }

}

class Designer: Worker, Designing{

    override fun designing(): String {
        return "drawing design"
    }

    override fun startWorking(): String {
        return designing()
    }

}