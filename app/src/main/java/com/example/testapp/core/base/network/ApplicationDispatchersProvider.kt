package com.example.testapp.core.base.network

import kotlinx.coroutines.Dispatchers

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class ApplicationDispatchersProvider : SchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}