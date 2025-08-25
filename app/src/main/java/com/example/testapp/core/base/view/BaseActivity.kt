package com.example.testapp.core.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.BR
import com.example.testapp.common.extension.updateStatusBar


/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    abstract val layoutResourceId: Int
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setLayoutIfDefined()
        updateStatusBar()
    }

    private fun setLayoutIfDefined() {
        if (this is ViewDataBindingOwner<*>) {
            setContentViewBinding(this, layoutResourceId)
            binding?.setVariable(BR.vm, viewModel)
            binding?.lifecycleOwner = this
            if (this is BaseView) {
                binding?.setVariable(BR.view, this)
            }
        } else {
            setContentView(layoutResourceId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is ViewDataBindingOwner<*>) {
            clearDataBinding()
        }
    }
}