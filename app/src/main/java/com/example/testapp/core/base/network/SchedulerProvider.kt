package com.example.testapp.core.base.network

import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}