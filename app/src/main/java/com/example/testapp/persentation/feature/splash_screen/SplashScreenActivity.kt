package com.example.testapp.persentation.feature.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testapp.R
import com.example.testapp.core.base.view.BaseComposeActivity
import com.example.testapp.persentation.feature.home_news.HomeNewsActivity
import kotlinx.coroutines.delay

/**
 * @author Andika Bratadirja
 * @date 13/09/2025
 */
class SplashScreenActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TestApp)
        super.onCreate(savedInstanceState)
    }

    @Composable
    override fun Content(
        modifier: Modifier
    ) {
        SplashScreen {
            startActivity(Intent(this, HomeNewsActivity::class.java))
            finish()
        }
    }
}

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(true) {
        delay(2000)
        onFinished()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null
        )
        Image(
            modifier = Modifier.padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._18sdp)),
            painter = painterResource(id = R.drawable.ic_text_splash),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun showScreenPreview() {
    SplashScreen() {}
}

