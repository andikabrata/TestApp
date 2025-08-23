package com.example.testapp.core.di

import com.example.testapp.persentation.feature.sample.SampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val viewModelModule = module {
    viewModel {
        SampleViewModel(
            sampleUseCase = get()
        )
    }
}