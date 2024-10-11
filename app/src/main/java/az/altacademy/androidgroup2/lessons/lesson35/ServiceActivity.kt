package az.altacademy.androidgroup2.lessons.lesson35

import android.Manifest
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
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
    private val notificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isPermissionGranted = it

        }

    private lateinit var receiver: MyReceiver

    private var myBoundService: MyBoundService? = null
    private var isConnected = false
    private val myServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? MyBoundService.MyBinder
            myBoundService = binder?.getServiceObject()
            isConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isConnected = false
        }

    }

    private lateinit var binding: ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindService(
            Intent(applicationContext, MyBoundService::class.java),
            myServiceConnection,
            Context.BIND_AUTO_CREATE
        )

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                receiver,
                IntentFilter(PlayerActions.CANCEL.toString()),
                Context.RECEIVER_EXPORTED
            )
        }else{
            registerReceiver(
                receiver,
                IntentFilter(PlayerActions.CANCEL.toString()),
            )
        }

        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)

        binding.startButton.setOnClickListener {
            startMyService(PlayerActions.START)
        }

        binding.playButton.setOnClickListener {
            myBoundService?.playMusic("https://www.music.com")
//            startMyService(PlayerActions.PLAY)
        }

        binding.pauseButton.setOnClickListener {
            startMyService(PlayerActions.PAUSE)
        }

        binding.stopButton.setOnClickListener {
            startMyService(PlayerActions.STOP)
        }
    }

    private fun startMyService(action: PlayerActions) {
        if (!isPermissionGranted) {
            notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            return
        }

        val intent = Intent(this, MyService::class.java)
        intent.action = action.toString()
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(myServiceConnection)
        isConnected = false
        unregisterReceiver(receiver)
    }

}