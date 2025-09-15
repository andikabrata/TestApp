package com.example.testapp.data.util

import com.example.testapp.core.base.network.ErrorData
import com.example.testapp.core.base.network.Resource

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
fun <T> Resource<T>.onSuccess(action: T?.() -> Unit) {
    if (this is Resource.Success) {
        action.invoke(model)
    }
}

fun <T> Resource<T>.onLoading(action: () -> Unit) {
    if (this is Resource.Loading) {
        action.invoke()
    }
}

fun <T> Resource<T>.onFailure(action: ErrorData.() -> Unit) {
    if (this is Resource.Error) {
        action.invoke(errorData)
    }
}