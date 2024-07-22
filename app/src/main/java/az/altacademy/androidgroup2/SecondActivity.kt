package az.altacademy.androidgroup2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.databinding.ActivitySecondBinding
import az.altacademy.androidgroup2.fragments.FirstFragment

class SecondActivity : AppCompatActivity() {

    private var binding: ActivitySecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, firstFragment)
            .commit()
    }
}