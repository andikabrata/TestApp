package com.example.testapp.domain.sample

import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.usecase.FlowUseCase
import com.example.testapp.data.model.sample.SampleModel
import com.example.testapp.data.repository.sample.SampleRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class SampleUseCase(
    private val repo: SampleRepository
) : FlowUseCase<Unit, SampleModel>() {
    override suspend fun execute(parameters: Unit?): Flow<Resource<SampleModel>> {
        return repo.login()
    }
}