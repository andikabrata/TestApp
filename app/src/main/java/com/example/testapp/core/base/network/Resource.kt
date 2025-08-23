package com.example.testapp.core.base.network

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
sealed class Resource<out T> {
    data class Success<T>(val model: T? = null, val source: DataSource) : Resource<T>()
    data class Error(val errorData: ErrorData) : Resource<Nothing>()
    data class MissingToken(val errorData: ErrorData):Resource<Nothing>()
    data class IncompleteProfile<T>(val model: T? = null, val source: DataSource) : Resource<T>()
    object Loading : Resource<Nothing>()
    object None : Resource<Nothing>()
}