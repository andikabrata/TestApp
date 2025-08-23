package com.example.testapp.core.base.network

import com.example.testapp.core.base.model.BaseResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class MissingToken<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int, val message: String? = null) : ResultWrapper<Nothing>()
    data class IncompleteProfile<out T>(val value: T) : ResultWrapper<T>()
}

internal suspend fun <T : BaseResponse> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ResultWrapper<T?> = withContext(dispatcher) {
    try {
        val call = apiCall.invoke()
        when (call!!.code) {
            0, 200, 32, 12 -> ResultWrapper.Success(call)
            1012, 1015, 1017 -> {
                ResultWrapper.MissingToken(call)
            }

            401 -> {
                ResultWrapper.IncompleteProfile(call)
            }

            else ->
                ResultWrapper.GenericError(
                    code = call.code ?: -1,
                    message = call.message
                )
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is TimeoutCancellationException -> {
                ResultWrapper.GenericError(
                    code = NetworkCodes.TIMEOUT_ERROR,
                    message = ErrorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                )
            }

            is IOException -> {
                ResultWrapper.GenericError(
                    code = NetworkCodes.CONNECTION_ERROR,
                    message = ErrorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                )
            }

            is HttpException -> {
                val code = throwable.code()
                ResultWrapper.GenericError(
                    code = code, message = throwable.localizedMessage
                )
            }

            else -> {
                ResultWrapper.GenericError(
                    code = NetworkCodes.GENERIC_ERROR,
                    message = throwable.localizedMessage
                )
            }
        }
    }
}