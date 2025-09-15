package com.example.testapp.core.di

import com.example.testapp.data.repository.home_news.HomeNewsRepository
import com.example.testapp.data.repository.home_news.HomeNewsRepositoryImpl
import com.example.testapp.data.repository.sample.SampleRepository
import com.example.testapp.data.repository.sample.SampleRepositoryImpl
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val repositoryModule = module {
    single<SampleRepository> {
        SampleRepositoryImpl(
            schedulerProvider = get(),
            sampleService = get(),
            sampleMapper = get()
        )
    }
    single<HomeNewsRepository> {
        HomeNewsRepositoryImpl(
            schedulerProvider = get(),
            homeNewsService = get(),
            mapper = get()
        )
    }
}