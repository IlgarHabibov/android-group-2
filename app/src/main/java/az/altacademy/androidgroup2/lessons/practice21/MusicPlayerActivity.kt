package az.altacademy.androidgroup2.lessons.practice21

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityMusicPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayerBinding
    private val viewModel by viewModels<MusicViewModel>()
    private var musicService: MusicService? = null
    private var isMusicServiceConnected = false

    private val musicServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? MusicService.MusicBinder
            musicService = binder?.getMusicService()
            isMusicServiceConnected = true
            onServiceConnected()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
            isMusicServiceConnected = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindService(
            Intent(this, MusicService::class.java),
            musicServiceConnection,
            Context.BIND_AUTO_CREATE
        )

        binding.playPauseButton.setOnClickListener {
            musicService?.playPauseTrack()
        }

        binding.prevButton.setOnClickListener {
            musicService?.prevTrack()
        }

        binding.nextButton.setOnClickListener {
            musicService?.nextTrack()
        }


        binding.trackProgress.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    musicService?.onUserProgressChange(progress)
                    binding.currentTime.text = getMinutesFromSeconds(progress)

                }
                seekBar?.setProgress(progress, true)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun onServiceConnected() {
        musicService?.setOnTrackChanged { track ->
            binding.trackName.text = track.name
            binding.prevButton.isEnabled = track.hasPrevTrack
            binding.nextButton.isEnabled = track.hasNextTrack
            binding.trackProgress.max = track.duration
            binding.currentTime.text = getMinutesFromSeconds(0)
            binding.totalTime.text = getMinutesFromSeconds(track.duration)
        }

        musicService?.setOnPlayingStateChanged { isPlaying ->
            val iconResId = if (isPlaying) R.drawable.ic_pause
            else R.drawable.ic_play
            binding.playPauseButton.setImageDrawable(
                ContextCompat.getDrawable(this, iconResId)
            )

        }

        musicService?.setOnTrackPositionChanged { position ->

            binding.trackProgress.setProgress(position, true)
            binding.currentTime.text = getMinutesFromSeconds(position)
        }
        musicService?.trackList = viewModel.createMusic()
    }

    private fun getMinutesFromSeconds(seconds: Int): String{
        val minutesText = seconds / 60
        val secondsText = seconds % 60
        return "$minutesText:$secondsText"
    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(musicServiceConnection)
        isMusicServiceConnected = false
    }
}