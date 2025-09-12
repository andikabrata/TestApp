package com.example.testapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.testapp.core.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class TestAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@TestAppApplication)
            fragmentFactory()
            modules(appModules)
        }
    }
}