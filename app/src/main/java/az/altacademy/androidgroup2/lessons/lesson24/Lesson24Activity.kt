package az.altacademy.androidgroup2.lessons.lesson24

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityLesson24Binding

class Lesson24Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLesson24Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson24Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navHost = supportFragmentManager.findFragmentById(R.id.bottomMenuContainer) as NavHostFragment
        val controller = navHost.navController

        val configuration = AppBarConfiguration(
            setOf(
                R.id.homePage,
                R.id.profilePage,
                R.id.eventsPage,
                R.id.historyPage
            )
        )
        binding.toolbar.setupWithNavController(controller, configuration)
        binding.bottomMenu.setupWithNavController(controller)
    }
}