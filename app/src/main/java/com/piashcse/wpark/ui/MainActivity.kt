package com.piashcse.wpark.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.piashcse.wpark.R
import com.piashcse.wpark.databinding.ActivityMainBinding
import com.rommansabbir.networkx.NetworkXProvider
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val snackBar: Snackbar by lazy {
        Snackbar.make(
            binding.snackBar,
            R.string.internet_connection_is_not_available,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.ok) {
            snackBar.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureNavController()
        internetConnection()
    }

    /**
     * Configure navController for back button
     */
    private fun configureNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /**
     * Monitor internet connection if not showing snackBar for better
     * user experience
     */
    private fun internetConnection() {
        NetworkXProvider.isInternetConnectedLiveData.observe(this) { status ->
            status?.let {
                if (it) {
                    snackBar.dismiss()
                } else {
                    snackBar.show()
                }
                Timber.d("Internet connection status: $it")
            }
        }
    }
}