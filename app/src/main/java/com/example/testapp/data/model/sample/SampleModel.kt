package com.example.testapp.data.model.sample

import com.example.testapp.core.base.model.BaseResponse

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
data class SampleModel(
    var data: List<UserModel>? = null
) : BaseResponse()

data class UserModel(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
)