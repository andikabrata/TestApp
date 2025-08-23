package com.example.testapp.data.model.sample

import com.example.testapp.core.base.model.BaseApiResponse

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class SampleModelResponse : BaseApiResponse() {
    val data: List<UserModelResponse>? = null
}

data class UserModelResponse(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
)