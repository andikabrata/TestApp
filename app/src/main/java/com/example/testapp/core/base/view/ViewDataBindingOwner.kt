package com.example.testapp.core.base.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface ViewDataBindingOwner<T : ViewDataBinding>  {
    var binding: T?

    val bindingNotNull: T
        get() = binding!!

    fun clearDataBinding(){
        binding = null
    }

    fun setViewBinding(view: View) {
        binding = DataBindingUtil.bind(view)!!
    }

    fun setContentViewBinding(activity: Activity, layoutResId: Int) {
        binding = DataBindingUtil.setContentView(activity, layoutResId)
    }

    fun inflateContentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        layoutResId: Int
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding!!.root
    }
}