package com.example.testapp.core.di

import com.example.testapp.data.mapper.LatestNewsModelResponseToLatestNewsModel
import com.example.testapp.data.mapper.SampleMapper
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
val mapperModule = module {
    factory { SampleMapper() }
    factory { LatestNewsModelResponseToLatestNewsModel() }
}