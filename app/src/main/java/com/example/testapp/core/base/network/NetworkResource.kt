package com.example.testapp.core.base.network

import com.example.testapp.core.base.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
abstract class NetworkResource<T : BaseResponse>(
    private val schedulerProvider: SchedulerProvider
) {
    fun asFlow(): Flow<Resource<T>> = flow {
        // check if should fetch data from remote or not
        if (shouldFetchFromRemote()) {
            val remoteResponse = safeApiCall(dispatcher = schedulerProvider.io()) {
                remoteFetch() // fetch the remote source provided
            }
            when (remoteResponse) {
                is ResultWrapper.Success -> {
                    if (shouldFetchRemoteAndSaveLocal()) {
                        remoteResponse.value?.let {
                            val localData = withContext(schedulerProvider.io()) {
                                saveLocal(it)
                                localFetch()
                            }
                            emit(
                                Resource.Success(
                                    model = localData,
                                    source = DataSource.CACHE
                                )
                            )
                        }
                    } else {
                        emit(
                            Resource.Success(
                                model = remoteResponse.value,
                                source = DataSource.REMOTE
                            )
                        )
                    }
                }

                is ResultWrapper.MissingToken -> {
                    val remote = safeApiCall(dispatcher = schedulerProvider.io()) {
                        remoteFetch()
                    }
                    when (remote) {
                        is ResultWrapper.Success -> {
                            emit(
                                Resource.Success(
                                    model = remote.value,
                                    source = DataSource.REMOTE
                                )
                            )
                        }

                        is ResultWrapper.GenericError -> {
                            emit(
                                Resource.Error(
                                    errorData = ErrorData(
                                        code = remote.code,
                                        message = remote.message
                                    )
                                )
                            )
                        }

                        is ResultWrapper.IncompleteProfile -> {
                            emit(
                                Resource.IncompleteProfile(
                                    model = remote.value,
                                    source = DataSource.REMOTE
                                )
                            )
                        }

                        is ResultWrapper.MissingToken -> {
                            emit(
                                Resource.Error(
                                    errorData = ErrorData(
                                        code = remote.value?.code ?: 999,
                                        message = remote.value?.message
                                    )
                                )
                            )
                        }
                    }
                }

                is ResultWrapper.IncompleteProfile -> {
                    emit(
                        Resource.IncompleteProfile(
                            model = remoteResponse.value,
                            source = DataSource.REMOTE
                        )
                    )
                }

                is ResultWrapper.GenericError -> {
                    emit(
                        Resource.Error(
                            errorData = ErrorData(
                                code = remoteResponse.code,
                                message = remoteResponse.message
                            )
                        )
                    )
                }
            }
        } else {
            // get from cache
            val localData = withContext(schedulerProvider.io()) {
                localFetch()
            }
            emit(Resource.Success(model = localData, DataSource.CACHE))
        }
    }

    abstract suspend fun  remoteFetch(): T
    open suspend fun saveLocal(data: T) {}
    open suspend fun localFetch(): T? = null
    open fun onFetchFailed(throwable: Throwable) = Unit
    open fun shouldFetchFromRemote() = true
    open fun shouldFetchRemoteAndSaveLocal() = false
}