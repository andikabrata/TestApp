package com.example.testapp.data.repository.sample

import com.example.testapp.core.base.network.Resource
import com.example.testapp.data.model.sample.SampleModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST


/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface SampleRepository {
    @POST("/video/api/v3/login")
    suspend fun login(): Flow<Resource<SampleModel>>
}
