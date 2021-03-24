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
        NavigationUI.setupActionBarWithNavController(this, navController)
        navViewModel.getNavEvent().observe(this) {
            navController.navigate(it.navDirections, it.extras)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}