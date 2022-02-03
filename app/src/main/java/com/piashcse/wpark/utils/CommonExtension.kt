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

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.pixelToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context.applicationContext)
        .load(imageUrl).placeholder(R.drawable.place_holder).into(this)
}

fun Context.showAlert(error: String) {
    BaseAlert(this).setTitleAndDescription(
        getString(R.string.error),
        error,
        getString(R.string.ok)
    ) {

    }
}