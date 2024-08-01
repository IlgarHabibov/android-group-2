package az.altacademy.androidgroup2.lessons.lesson21

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.lessons.lesson22.PagerFragment

class Lesson21Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson21)

        supportFragmentManager.beginTransaction()
            .replace(R.id.listContainer, ListFragment())
            .commit()

    }
}