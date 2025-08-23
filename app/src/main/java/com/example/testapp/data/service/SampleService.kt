package com.example.testapp.data.service

import com.example.testapp.data.model.sample.SampleModelResponse
import retrofit2.http.GET

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface SampleService {
    @GET("api/1408693546105626624")
    suspend fun login(): SampleModelResponse
}