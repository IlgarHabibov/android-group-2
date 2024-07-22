package az.altacademy.androidgroup2.navigationview

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import az.altacademy.androidgroup2.R
import az.altacademy.androidgroup2.databinding.ActivityNavigationViewBinding
import az.altacademy.androidgroup2.navigationview.fragments.EventsFragment
import az.altacademy.androidgroup2.navigationview.fragments.HistoryFragment
import az.altacademy.androidgroup2.navigationview.fragments.HomeFragment
import az.altacademy.androidgroup2.navigationview.fragments.ProfileFragment

class NavigationViewActivity : AppCompatActivity() {

    private var binding: ActivityNavigationViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding?.mainDrawer,
            binding?.toolbar,
            R.string.drawerOpen,
            R.string.drawerClose
        )

        binding?.mainDrawer?.addDrawerListener(toggle)
        toggle.syncState()

        binding?.navigationView?.setNavigationItemSelectedListener { selectedMenuItem ->
            changeFragmentOnMenuSelect(selectedMenuItem)
            true
        }

        changeFragment(HomeFragment(), getString(R.string.home))
        binding?.navigationView?.setCheckedItem(R.id.homePage)
    }

    private fun changeFragmentOnMenuSelect(item: MenuItem) {
        when (item.itemId) {
            R.id.homePage -> changeFragment(HomeFragment(), getString(R.string.home))
            R.id.eventsPage -> changeFragment(EventsFragment(), getString(R.string.events))
            R.id.historyPage -> changeFragment(HistoryFragment(), getString(R.string.history))
            R.id.profilePage -> changeFragment(ProfileFragment(), getString(R.string.profile))
            else -> HomeFragment()
        }
    }

    private fun changeFragment(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
        binding?.toolbar?.title = title
        binding?.mainDrawer?.closeDrawer(GravityCompat.START)

    }

    override fun onBackPressed() {
        if (binding?.mainDrawer?.isDrawerOpen(GravityCompat.START) == true) {
            binding?.mainDrawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}