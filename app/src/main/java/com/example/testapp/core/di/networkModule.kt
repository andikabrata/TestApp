package com.example.testapp.core.di

import com.example.testapp.core.base.network.ApplicationDispatchersProvider
import com.example.testapp.core.base.network.SchedulerProvider
import com.example.testapp.data.service.SampleService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */

private const val TIMEOUT: Long = 60
val networkModule = module {
    single { GsonBuilder().create() }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor(logging)
        }.build()
    }

    single<Retrofit>(named("sampleMock")) {
        Retrofit.Builder()
            .baseUrl("https://jsonblob.com/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory<SchedulerProvider> { ApplicationDispatchersProvider() }
    factory { get<Retrofit>(qualifier = named("sampleMock")).create(SampleService::class.java) }
}