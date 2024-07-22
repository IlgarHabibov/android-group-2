package az.altacademy.androidgroup2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private var binding: ActivityFirstBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val text = intent.getIntExtra(Constants.FULL_NAME, 0)
        val age = intent.getIntExtra(Constants.AGE, 0)

        binding?.firstActivityTitle?.text = (text + age).toString()
        binding?.firstActivityTitle?.text = "https://atlacademy.az/"
        binding?.firstActivityTitle?.text = "https://atlacademy.az/"


        binding?.backFromFirst?.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("first", "${text * 2}")
//            setResult(Constants.FIRST_ACTIVITY_RESULT_CODE, intent)
//            finish()

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}