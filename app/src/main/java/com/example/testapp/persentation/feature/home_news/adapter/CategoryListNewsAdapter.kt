package com.example.testapp.persentation.feature.home_news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapp.core.base.view.BaseListAdapter
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.databinding.ItemCategoryListNewsBinding
import com.example.testapp.persentation.feature.home_news.diffutil.LatestNewsDiffUtil

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class CategoryListNewsAdapter(
    private val onItemClick: (LatestNews) -> Unit
) : BaseListAdapter<LatestNews, CategoryListNewsAdapter.ViewHolder>(
    diffUtil = LatestNewsDiffUtil()
) {
    override val isIncludeFooter: Boolean
        get() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryListNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(val binding: ItemCategoryListNewsBinding) :
        BaseViewHolder<LatestNews>(binding = binding) {
        override fun bindData(data: LatestNews) {
            binding.apply {
                this.data = data
                executePendingBindings()
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }
}