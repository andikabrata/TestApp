package com.example.testapp.persentation.feature.sample

import android.os.Bundle
import com.example.testapp.R
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.databinding.ActivitySampleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleActivity : BaseActivity<SampleViewModel>(), ViewDataBindingOwner<ActivitySampleBinding>, SampleView {
    override val layoutResourceId: Int = R.layout.activity_sample
    override val viewModel: SampleViewModel by viewModel()
    override var binding: ActivitySampleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeText()
        viewModel.getResponse()
    }

    private fun observeText() {
        observeData(viewModel.response) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        viewModel.text.value = it.model?.data?.get(0)?.title
                    }

                    is Resource.Error -> {}
                    else -> {}
                }
            }
        }
    }
}