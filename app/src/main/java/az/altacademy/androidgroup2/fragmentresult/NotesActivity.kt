package az.altacademy.androidgroup2.fragmentresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    private val noteResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if (result.resultCode == 728){
            val text = result.data?.getStringExtra("note")
            binding.note.text = text
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            noteResult.launch(intent)
        }
    }
}