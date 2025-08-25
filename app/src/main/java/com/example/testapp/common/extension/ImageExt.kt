package com.example.testapp.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}