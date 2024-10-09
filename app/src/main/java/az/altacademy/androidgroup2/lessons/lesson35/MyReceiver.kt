package az.altacademy.androidgroup2.lessons.lesson35

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    private var onStateChange: ((String) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        onStateChange?.invoke(intent?.action ?: "Error")
    }

    fun onStateChange(state: (String)-> Unit){
        this.onStateChange = state
    }
}