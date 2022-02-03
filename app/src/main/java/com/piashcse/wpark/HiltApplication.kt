package com.piashcse.wpark

import androidx.multidex.MultiDexApplication
import com.rommansabbir.networkx.NetworkXConfig
import com.rommansabbir.networkx.NetworkXProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        networkState()
        timber()

    }

    private fun timber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun networkState() {
        val builder = NetworkXConfig.Builder()
            .withApplication(this)
            // You can disable speed meter if not required
            .withEnableSpeedMeter(true)
            .build()
        NetworkXProvider.enable(builder)
    }
}