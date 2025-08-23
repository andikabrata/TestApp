package com.example.testapp.core.base.usecase

import com.example.testapp.core.base.network.ErrorData
import com.example.testapp.core.base.network.NetworkCodes
import com.example.testapp.core.base.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
abstract class FlowUseCase<in P, R>() {
    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch { e ->
            Resource.Error(
                ErrorData(
                    code = NetworkCodes.GENERIC_ERROR,
                    message = e.localizedMessage
                )
            )
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}
