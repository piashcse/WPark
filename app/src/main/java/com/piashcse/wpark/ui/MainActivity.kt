package com.piashcse.wpark.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.piashcse.wpark.R
import com.piashcse.wpark.databinding.ActivityMainBinding
import com.rommansabbir.networkx.NetworkXProvider
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
        internetConnection()
    }

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