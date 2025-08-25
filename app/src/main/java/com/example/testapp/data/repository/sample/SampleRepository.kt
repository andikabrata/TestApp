package com.example.testapp.data.repository.sample

import com.example.testapp.core.base.network.Resource
import com.example.testapp.data.model.sample.SampleModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface SampleRepository {
    @Throws(Exception::class)
    suspend fun sample(): Flow<Resource<SampleModel>>
}
