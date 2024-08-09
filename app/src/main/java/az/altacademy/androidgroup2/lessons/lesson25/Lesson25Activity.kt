package az.altacademy.androidgroup2.lessons.lesson25

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityLesson25Binding

class Lesson25Activity : AppCompatActivity() {

    lateinit var binding: ActivityLesson25Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson25Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}