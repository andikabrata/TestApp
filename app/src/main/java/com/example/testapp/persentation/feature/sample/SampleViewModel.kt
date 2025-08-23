package com.example.testapp.persentation.feature.sample

import androidx.lifecycle.MutableLiveData
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.sample.SampleModel
import com.example.testapp.domain.sample.SampleUseCase
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
class SampleViewModel(
    private val sampleUseCase: SampleUseCase
) : BaseViewModel() {

    var response = MutableLiveData<Resource<SampleModel>>()
    val text = MutableLiveData("")

    fun getResponse() {
        launch {
            sampleUseCase().collect {
                response.value = it
            }
        }
    }
}