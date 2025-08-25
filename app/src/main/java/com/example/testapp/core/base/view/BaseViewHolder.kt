package com.example.testapp.core.base.view

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
abstract class BaseViewHolder<T>(
    binding: ViewDataBinding? = null,
    view: View? = null
) : RecyclerView.ViewHolder(
    binding?.root ?: view ?: throw IllegalStateException("Please use either binding or view")
) {
    abstract fun bindData(data: T)
}