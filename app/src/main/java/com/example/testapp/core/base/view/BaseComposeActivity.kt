package com.example.testapp.core.base.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

/**
 * @author Andika Bratadirja
 * @date 13/09/2025
 */
abstract class BaseComposeActivity<VM : ViewModel> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            AppTheme {
                Content(Modifier)
            }
        }
    }

    @Composable
    fun AppTheme(content: @Composable () -> Unit) {
        val colors = lightColorScheme(
            background = Color.White,
            surface = Color.White
        )
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }

    /**
     * Override function ini di subclass untuk menampilkan UI
     */
    @Composable
    protected abstract fun Content(modifier: Modifier)
}