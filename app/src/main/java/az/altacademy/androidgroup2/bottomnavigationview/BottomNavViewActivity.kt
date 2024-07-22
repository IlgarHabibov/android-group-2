package az.altacademy.androidgroup2.bottomnavigationview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityBottomNavViewBinding
import az.altacademy.androidgroup2.databinding.ActivityNavigationViewBinding
import az.altacademy.androidgroup2.navigationview.fragments.EventsFragment
import az.altacademy.androidgroup2.navigationview.fragments.HistoryFragment
import az.altacademy.androidgroup2.navigationview.fragments.HomeFragment
import az.altacademy.androidgroup2.navigationview.fragments.ProfileFragment

class BottomNavViewActivity : AppCompatActivity() {
    private var binding: ActivityBottomNavViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        binding?.bottomMenu?.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.homePage -> changeMyFragment(HomeFragment(), getString(R.string.home))
                R.id.eventsPage -> changeMyFragment(EventsFragment(), getString(R.string.events))
                R.id.historyPage -> changeMyFragment(HistoryFragment(), getString(R.string.history))
                R.id.profilePage -> changeMyFragment(ProfileFragment(), getString(R.string.profile))
                else -> HomeFragment()
            }

            true
        }

        changeMyFragment(HomeFragment(), getString(R.string.home))

    }
    private fun changeMyFragment(newFragment: Fragment, title: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.newMenuContainer, newFragment)
            .commit()
        binding?.toolbar?.title = title
    }
}