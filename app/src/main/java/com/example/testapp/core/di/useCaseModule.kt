package com.example.testapp.core.di

import com.example.testapp.domain.sample.SampleUseCase
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val useCaseModule = module {
    single { SampleUseCase(get()) }
}