package com.example.testapp.core.base.view

import androidx.recyclerview.widget.RecyclerView

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
abstract class BaseSimpleAdapter<T : RecyclerView.Adapter<out RecyclerView.ViewHolder>> :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: T? = null

    fun setData(data: T) {
        this.data = data
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        data?.let { holder.bindData(it) }
    }
}