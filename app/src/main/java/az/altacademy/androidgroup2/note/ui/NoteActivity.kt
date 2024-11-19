package az.altacademy.androidgroup2.note.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityNoteBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = firebaseAuth.currentUser?.uid

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.noteContainer) as NavHostFragment
        val controller = navHostFragment.navController

        val graph = controller.navInflater.inflate(R.navigation.note_graph)
        if (userId == null){
            graph.setStartDestination(R.id.noteRegisterFragment)
        }else{
            graph.setStartDestination(R.id.noteListFragment)
        }

        controller.setGraph(graph, null)

    }
}