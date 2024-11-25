package az.altacademy.androidgroup2.note.ui

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityNoteBinding
import az.altacademy.androidgroup2.extensions.setStatusBarColors
import az.altacademy.androidgroup2.utils.LanguageHelper
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    private var sharedPreference: SharedPreferences? = null

    private val themeVM by viewModels<ThemeVM>()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColors(R.color.background)

        LanguageHelper.applyLocale(this)
        Log.d("asdasdasdasd", "recreate: ")

        sharedPreference = getPreferences(MODE_PRIVATE)


        val userId = firebaseAuth.currentUser?.uid

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.noteContainer) as NavHostFragment
        val controller = navHostFragment.navController

        val graph = controller.navInflater.inflate(R.navigation.note_graph)
        if (userId == null) {
            graph.setStartDestination(R.id.noteRegisterFragment)
        } else {
            graph.setStartDestination(R.id.noteListFragment)
        }

        controller.setGraph(graph, null)

        themeVM.theme.observe(this) { isDarkMode ->
            setTheme(isDarkMode)
            setDarkMode(isDarkMode)
        }

    }

    override fun onResume() {
        super.onResume()
        checkTheme()
    }

    private fun checkTheme() {

        when (isDark()) {
            true -> themeVM.setDarkMode(true)
            false -> themeVM.setDarkMode(false)
            else -> {
                setTheme(getSystemTheme())
            }
        }
    }

    private fun setTheme(isDarkMode: Boolean?) {
        if (isDarkMode == null) return
        if (isDarkMode == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun getSystemTheme(): Boolean {
        val darkModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkModeOn = darkModeFlags == Configuration.UI_MODE_NIGHT_YES
        return isDarkModeOn
    }

    private fun isDark(): Boolean? {
        return sharedPreference?.getBoolean("theme", false)
    }

    private fun setDarkMode(isDarkMode: Boolean?) {
        isDarkMode?.let {
            sharedPreference?.edit()?.putBoolean("theme", it)?.apply()
        }

    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLocale(newBase))
    }
}