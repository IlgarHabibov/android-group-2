package az.altacademy.androidgroup2.homework19

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R

class HomeWork19Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work19)

        supportFragmentManager.beginTransaction()
            .replace(R.id.homework_19_container, CreateAccountFragment())
            .commit()
        }

}