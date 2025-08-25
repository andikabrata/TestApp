package com.example.testapp.common.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.testapp.common.extension.DateExt.formatDate
import com.example.testapp.common.extension.loadImage

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("image")
    fun ImageView.setImage(url: String?) {
        loadImage(url)
    }

    @JvmStatic
    @BindingAdapter("publishDate")
    fun TextView.publishDate(date: String) {
        this.text = formatDate(date)
    }
}