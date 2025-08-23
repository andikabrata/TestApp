package com.example.testapp.core.base.network

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
object ErrorCodesMapper {
    fun getMessage(errorCode: Int) = when (errorCode) {
        NetworkCodes.CONNECTION_ERROR,
        NetworkCodes.TIMEOUT_ERROR -> "Failed to connect to server please check you network!"

        else -> "Something went wrong please try again!"
    }
}