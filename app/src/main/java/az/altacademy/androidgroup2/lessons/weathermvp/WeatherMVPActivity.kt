package az.altacademy.androidgroup2.lessons.weathermvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import az.altacademy.androidgroup2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherMVPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_mvp)
    }
}