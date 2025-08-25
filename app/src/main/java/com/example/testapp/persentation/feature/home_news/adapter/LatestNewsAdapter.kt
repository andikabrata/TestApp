package com.example.testapp.persentation.feature.home_news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapp.core.base.view.BaseListAdapter
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.databinding.ItemLatestNewsBinding
import com.example.testapp.persentation.feature.home_news.diffutil.LatestNewsDiffUtil

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class LatestNewsAdapter(
    private val onItemClick: (LatestNews) -> Unit
) : BaseListAdapter<LatestNews, LatestNewsAdapter.ViewHolder>(
    diffUtil = LatestNewsDiffUtil()
) {
    override val isIncludeFooter: Boolean
        get() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemLatestNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(val binding: ItemLatestNewsBinding) :
        BaseViewHolder<LatestNews>(binding = binding) {
        override fun bindData(data: LatestNews) {
            binding.data = data
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClick.invoke(data)
            }
        }
    }
}