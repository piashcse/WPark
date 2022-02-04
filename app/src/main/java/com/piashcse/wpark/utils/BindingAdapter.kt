package com.piashcse.wpark.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.piashcse.wpark.R

/**
 * data binding adapter for loading image in XML level from imageView
 */
@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageLink: String?) {
    imageLink?.let {
        Glide.with(imageView.context.applicationContext)
            .load(it).placeholder(R.drawable.place_holder).into(imageView)
    }
}