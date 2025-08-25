package com.example.testapp.common.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.testapp.R

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */

fun AppCompatActivity.updateStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    val controller = WindowInsetsControllerCompat(window, window.decorView)

    if (isDarkMode()) {
        // Background gelap, ikon putih
        window.statusBarColor = getColor(R.color.background_dark)
        controller.isAppearanceLightStatusBars = false
    } else {
        // Background putih, ikon hitam
        window.statusBarColor = getColor(R.color.white)
        controller.isAppearanceLightStatusBars = true
    }
}

fun AppCompatActivity.isDarkMode(): Boolean {
    val currentNightMode = resources.configuration.uiMode and
            android.content.res.Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
}