package com.example.testapp.common.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}