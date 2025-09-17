package com.example.testapp.core.di

import com.example.testapp.persentation.feature.detail_news.DetailNewsViewModel
import com.example.testapp.persentation.feature.home_news.HomeNewsViewModel
import com.example.testapp.persentation.feature.notification.NotificationViewModel
import com.example.testapp.persentation.feature.sample.SampleViewModel
import com.example.testapp.persentation.feature.search.SearchNewsViewModel
import com.example.testapp.persentation.feature.splash_screen.SplashScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val viewModelModule = module {
    viewModelOf(::SampleViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::HomeNewsViewModel)
    viewModelOf(::DetailNewsViewModel)
    viewModelOf(::NotificationViewModel)
    viewModelOf(::SearchNewsViewModel)
}