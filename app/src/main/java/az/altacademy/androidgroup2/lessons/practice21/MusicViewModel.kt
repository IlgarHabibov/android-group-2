package az.altacademy.androidgroup2.lessons.practice21

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableLiveData<List<MusicTrackModel>>()
    val state: LiveData<List<MusicTrackModel>> = _state

    fun getMusicList(){
        _state.value = createMusic()
    }


    fun createMusic() = listOf(
        MusicTrackModel(
            name = "Blue Skies",
            trackUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        MusicTrackModel(
            name = "Upbeat and Positive",
            trackUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"
        ),
        MusicTrackModel(
            name = "Corporate Background",
            trackUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3"
        ),
        MusicTrackModel(
            name = "Relaxing Guitar",
            trackUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3"
        ),
        MusicTrackModel(
            name = "Ambient Chill",
            trackUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3"
        ),

    )
}