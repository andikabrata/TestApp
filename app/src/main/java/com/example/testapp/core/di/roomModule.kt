package com.example.testapp.core.di

import com.example.testapp.data.repository.home_news.NewsLocalSource
import com.example.testapp.data.repository.home_news.NewsLocalSourceImpl
import com.example.testapp.data.repository.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
val roomModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().newsDao() }
    factory<NewsLocalSource> {
        NewsLocalSourceImpl(
            newsDao = get()
        )
    }
}