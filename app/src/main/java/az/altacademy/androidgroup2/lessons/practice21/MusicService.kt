package az.altacademy.androidgroup2.lessons.practice21

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

@AndroidEntryPoint
class MusicService : Service(), CoroutineScope {

    var trackList = listOf<MusicTrackModel>()
        set(value) {
            field = value

            getNewMusicFromList()
        }


    private var currentTrackIndex = 0
    private var currentMusic: MusicTrackModel? = null

    private var musicPlayer: MediaPlayer? = null

    private var onTrackChanged: ((PlayerTrackModel) -> Unit)? = null
    private var onTrackPositionChanged: ((Int) -> Unit)? = null
    private var onPlayingStateChanged: ((Boolean) -> Unit)? = null

    fun setOnTrackChanged(onTrackChanged: (PlayerTrackModel) -> Unit) {
        this.onTrackChanged = onTrackChanged
    }

    fun setOnTrackPositionChanged(onTrackPositionChanged: (Int) -> Unit) {
        this.onTrackPositionChanged = onTrackPositionChanged
    }

    fun setOnPlayingStateChanged(onPlayingStateChanged: (Boolean) -> Unit) {
        this.onPlayingStateChanged = onPlayingStateChanged
    }


    override fun onBind(intent: Intent): IBinder {
        return MusicBinder()
    }

    override fun onCreate() {
        super.onCreate()

    }

    inner class MusicBinder() : Binder() {
        fun getMusicService() = this@MusicService
    }

    private fun getNewMusicFromList() {
        if (trackList.size <= currentTrackIndex) return
        val track = trackList[currentTrackIndex]
        val uri = Uri.parse(track.trackUrl)
        musicPlayer?.stop()
        musicPlayer = MediaPlayer.create(this, uri)
        musicPlayer?.isLooping = false
        val durationInMilliSeconds = musicPlayer?.duration ?: 0
        val duration = if (durationInMilliSeconds > 0)
            durationInMilliSeconds / 1000
        else 0

        val playerTrack = PlayerTrackModel(
            name = track.name,
            duration = duration,
            hasNextTrack = hasNext(currentTrackIndex),
            hasPrevTrack = hasPrev(currentTrackIndex)
        )
        startProgressUpdater()
        trackChanged(playerTrack)
    }

    private fun hasNext(trackIndex: Int) =
        trackList.size - 1 > trackIndex

    private fun hasPrev(trackIndex: Int) =
        trackIndex - 1 >= 0

    private fun trackChanged(playerTrackModel: PlayerTrackModel) {
        onTrackChanged?.invoke(playerTrackModel)
    }

    fun playPauseTrack() {
        startProgressUpdater()
        if (musicPlayer?.isPlaying == true) {
            musicPlayer?.pause()
            onPlayingStateChanged?.invoke(false)
        } else {
            musicPlayer?.start()
            onPlayingStateChanged?.invoke(true)
        }
    }

    private fun startProgressUpdater() {
        Log.d("ASDKBJASDAS", "startProgressUpdater1:")

        launch {
            while (musicPlayer?.isPlaying == true) {
                Log.d("ASDKBJASDAS", "startProgressUpdater2:")
                val currentPosition = musicPlayer?.currentPosition ?: 0
                val duration = musicPlayer?.duration ?: 0
                updateProgress(currentPosition, duration)
                delay(1000) // Update every second
            }
        }
    }

    private fun updateProgress(currentPosition: Int, duration: Int) {
        val position = if (currentPosition > 0) currentPosition / 1000 else currentPosition
        onTrackPositionChanged?.invoke(position)
    }

    fun nextTrack(){
        currentTrackIndex++
        getNewMusicFromList()
        playPauseTrack()
    }

    fun prevTrack(){
        currentTrackIndex --
        getNewMusicFromList()
        playPauseTrack()
    }

    fun onUserProgressChange(position: Int){
        musicPlayer?.seekTo(position * 1000)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()


}