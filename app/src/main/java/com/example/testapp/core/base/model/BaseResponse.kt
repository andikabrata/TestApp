package com.example.testapp.core.base.model

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
open class BaseResponse {
    var code: Int? = null
    var status: String? = ""
    var message: String? = ""
    var totalResults: String? = ""
}