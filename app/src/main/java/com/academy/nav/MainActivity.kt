package com.academy.nav

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.academy.nav.ui.details.DetailsFragment
import com.academy.nav.ui.home.HomeFragment
import com.academy.nav.ui.navigation.Destination
import com.academy.nav.ui.navigation.NavigationViewModel
import com.academy.nav.ui.settings.SettingsFragment
import com.academy.ui_favorites.FavoritesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container_view)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        navViewModel.getNavEvent().observe(this) {
            when (it.destination) {
                Destination.DETAILS -> replace<DetailsFragment>(bundleOf("movieId" to it.movie?.id))
                Destination.SETTINGS -> replace<SettingsFragment>()
                Destination.FAVORITES -> replace<FavoritesFragment>()
            }
        }
    }

    private inline fun <reified T : Fragment> replace(bundle: Bundle? = null) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(T::class.java.simpleName)
            replace<T>(R.id.fragment_container_view, args = bundle)
        }
    }
}