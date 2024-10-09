package az.altacademy.androidgroup2.lessons.lesson35

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityServiceBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ServiceActivity : AppCompatActivity() {

    private var isPermissionGranted = false
    private val notificationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isPermissionGranted = it

    }

    private lateinit var receiver: MyReceiver

    private lateinit var binding: ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = MyReceiver()
        receiver.onStateChange {
            lifecycleScope.launch {
                delay(1000)
                Snackbar.make(binding.root, "action = $it", Toast.LENGTH_SHORT)
                    .setAction("Close", {

                    })
                    .show()
            }
        }
        registerReceiver(receiver, IntentFilter(PlayerActions.CANCEL.toString()))

        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)

        binding.startButton.setOnClickListener {
            startMyService(PlayerActions.START)
        }

        binding.playButton.setOnClickListener {
            startMyService(PlayerActions.PLAY)
        }

        binding.pauseButton.setOnClickListener {
            startMyService(PlayerActions.PAUSE)
        }

        binding.stopButton.setOnClickListener {
            startMyService(PlayerActions.STOP)
        }
    }

    private fun startMyService(action: PlayerActions){
        if (!isPermissionGranted){
            notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            return
        }

        val intent = Intent(this, MyService::class.java)
        intent.action = action.toString()
        startForegroundService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}