package com.academy.nav

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.academy.nav.ui.navigation.Destination
import com.academy.nav.ui.navigation.NavigationViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navViewModel: NavigationViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragment_container_view)
        // TODO Step 16 - Uncomment the line below and the title in the action bar will automatically be updated when
        //  the destination changes and the ActionBar will show the Up button when it needed.
        //NavigationUI.setupActionBarWithNavController(this, navController)

        navViewModel.getNavEvent().observe(this) {
            when (it.destination) {
                Destination.DETAILS -> navController.navigate(R.id.detailsFragment, bundleOf("movieId" to it.movie?.id))
                Destination.SETTINGS -> navController.navigate(R.id.settingsFragment)
                Destination.FAVORITES -> navController.navigate(R.id.favoritesFragment)
            }
            // TODO Step 18 - Remove the when with it content and use the commented navigate() function below instead
/*            navController.navigate(
                it.navDirections.actionId,
                it.navDirections.arguments,
                it.navOptions,
                it.extras
            )*/
        }
    }

    // TODO Step 17 - Uncomment function onSupportNavigateUp()
    //  This method is called whenever the user chooses to navigate Up within your application's activity hierarchy from the action bar.
/*    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}