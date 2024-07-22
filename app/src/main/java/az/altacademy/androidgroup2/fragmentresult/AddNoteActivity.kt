package az.altacademy.androidgroup2.fragmentresult

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityAddNoteBinding
import az.altacademy.androidgroup2.databinding.ActivityNotesBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveNote.setOnClickListener {

            val text = binding.noteInput.text.toString()
            val intent = Intent(this, NotesActivity::class.java)
            intent.putExtra("note", text)
            setResult(728, intent)
            finish()
        }
    }
}