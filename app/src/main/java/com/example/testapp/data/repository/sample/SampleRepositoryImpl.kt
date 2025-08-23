package com.example.testapp.data.repository.sample

import com.example.testapp.core.base.network.NetworkResource
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.network.SchedulerProvider
import com.example.testapp.data.mapper.SampleMapper
import com.example.testapp.data.model.sample.SampleModel
import com.example.testapp.data.service.SampleService
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class SampleRepositoryImpl(
    private val schedulerProvider: SchedulerProvider,
    private val sampleService: SampleService,
    private val sampleMapper: SampleMapper
) : SampleRepository {
    override suspend fun login(): Flow<Resource<SampleModel>> {
        return object : NetworkResource<SampleModel>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): SampleModel {
                val request = sampleService.login()
                return sampleMapper.map(request)
            }

        }.asFlow()
    }
}