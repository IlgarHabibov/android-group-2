package az.altacademy.androidgroup2.lessons.lesson23

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityLesson23Binding
import az.altacademy.androidgroup2.databinding.ActivityLesson24Binding

class Lesson23Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLesson23Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLesson23Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.activityContainer) as NavHostFragment
        val controller = navHost.navController
        binding.toolbar.setupWithNavController(controller)
    }
}