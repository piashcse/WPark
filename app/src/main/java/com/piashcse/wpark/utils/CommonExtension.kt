package com.piashcse.wpark.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.piashcse.wpark.R
import com.piashcse.wpark.utils.base.BaseAlert
import com.rommansabbir.networkx.NetworkXProvider
import timber.log.Timber

/**
 * hiding view extension function
 */
fun View.hide() {
    this.visibility = View.GONE
}

/**
 * showing view extension function
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * convert dp to pixel extension function
 */
fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * convert pixel to dp extension function
 */
fun Int.pixelToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * check internet connection if available return true else false
 */
fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

/**
 * loading image extension function using glide with placeholder
 */
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context.applicationContext)
        .load(imageUrl).placeholder(R.drawable.place_holder).into(this)
}

/**
 * Common alert for showing error or necessary message in UI
 */
fun Context.showAlert(error: String) {
    BaseAlert(this).setTitleAndDescription(
        getString(R.string.error),
        error,
        getString(R.string.ok)
    ) {

    }
}