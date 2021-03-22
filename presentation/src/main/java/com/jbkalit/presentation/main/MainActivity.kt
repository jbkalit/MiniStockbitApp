package com.jbkalit.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.jbkalit.presentation.R
import com.jbkalit.presentation.databinding.ActivityMainBinding
import com.jbkalit.presentation.main.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val viewModel by viewModel<MainActivityViewModel>()
    private var isHideMenu = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.hostFragment) as NavHostFragment? ?: return
        navController = navHostFragment.navController

        setUpNavigation()
    }

    private fun setUpNavigation() {
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        setupActionBarWithNavController(
            navHostFragment.navController, AppBarConfiguration(
                topLevelDestinationIds = setOf(
                    R.id.watchListFragment,
                    R.id.dataFeedFragment,
                    R.id.loginFragment
                )
            )
        )
    }

    fun hideMenu(state: Boolean) {
        isHideMenu = state
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        if (isHideMenu) {
            menu?.findItem(R.id.logOut)?.isVisible = false
            binding.bottomNavigation.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> {
                viewModel.logOut()
                navHostFragment.findNavController().navigate(R.id.loginFragment, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.graph_root_navigation).navigateUp()
    }

}
