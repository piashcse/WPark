package com.piashcse.wpark

import androidx.multidex.MultiDexApplication
import com.piashcse.wpark.data.repository.DatabaseRepository
import com.rommansabbir.networkx.NetworkXConfig
import com.rommansabbir.networkx.NetworkXProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application starting point
 */
@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        networkState()
        timber()

    }

    /**
     * Timber for logging showing debug infor
     */
    private fun timber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Added network state monitoring method for observing network continuously
     */
    private fun networkState() {
        val builder = NetworkXConfig.Builder()
            .withApplication(this)
            // You can disable speed meter if not required
            .withEnableSpeedMeter(true)
            .build()
        NetworkXProvider.enable(builder)
    }
}