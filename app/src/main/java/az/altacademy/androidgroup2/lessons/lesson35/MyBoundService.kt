package az.altacademy.androidgroup2.lessons.lesson35

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MyBoundService: Service() {

    val binder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class MyBinder: Binder(){
        fun getServiceObject(): MyBoundService{
            return this@MyBoundService
        }
    }

    fun playMusic(url: String){
        Toast.makeText(this, "Playing music $url", Toast.LENGTH_SHORT).show()
        //
    }
}