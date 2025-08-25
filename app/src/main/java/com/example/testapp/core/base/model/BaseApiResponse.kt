package com.example.testapp.core.base.model

import com.google.gson.annotations.SerializedName

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
open class BaseApiResponse {
    @SerializedName("code")
    var code: Int? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("totalResults")
    var totalResults: String? = null
}