package com.example.testapp.core.di

import com.example.testapp.domain.home_news.GetCategoryListNewsUseCase
import com.example.testapp.domain.home_news.GetLatestNewsUseCase
import com.example.testapp.domain.sample.SampleUseCase
import com.example.testapp.domain.search.GetSearchNewsUseCase
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val useCaseModule = module {
    single { SampleUseCase(get()) }
    single { GetLatestNewsUseCase(get()) }
    single { GetCategoryListNewsUseCase(get()) }
    single { GetSearchNewsUseCase(get()) }
}