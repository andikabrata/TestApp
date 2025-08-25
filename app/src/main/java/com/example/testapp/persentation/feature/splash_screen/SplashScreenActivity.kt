package com.example.testapp.persentation.feature.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.testapp.R
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.databinding.ActivitySplashScreenBinding
import com.example.testapp.persentation.feature.home_news.HomeNewsActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<SplashScreenViewModel>(),
    ViewDataBindingOwner<ActivitySplashScreenBinding>, SplashScreenView {
    override val layoutResourceId: Int = R.layout.activity_splash_screen
    override val viewModel: SplashScreenViewModel by viewModel()
    override var binding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            goToHome()
        }
    }

    private fun setupSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            false
        }
    }

    private fun goToHome() {
        HomeNewsActivity.startActivity(this)
        finish()
    }
}