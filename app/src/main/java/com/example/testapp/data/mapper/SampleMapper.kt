package com.example.testapp.data.mapper

import com.example.testapp.core.base.mapper.BaseMapper
import com.example.testapp.data.model.sample.SampleModel
import com.example.testapp.data.model.sample.SampleModelResponse
import com.example.testapp.data.model.sample.UserModel

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class SampleMapper : BaseMapper<SampleModelResponse, SampleModel>() {
    override fun map(value: SampleModelResponse): SampleModel {
        return SampleModel().apply {
            code = value.code
            message = value.message
            status = value.status
            data = value.data?.map {
                UserModel().apply {
                    id = it.id
                    userId = it.userId
                    title = it.title
                    body = it.body
                }
            }
        }
    }
}