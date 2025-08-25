package com.example.testapp.persentation.feature.home_news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.common.utils.customviews.LinearSpacingItemDecoration
import com.example.testapp.core.base.view.BaseSimpleAdapter
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.databinding.ItemContainerCategoryNewsBinding

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class ContainerCategoryNewsAdapter : BaseSimpleAdapter<CategoryNewsAdapter>() {
    override fun getItemViewType(position: Int): Int = R.layout.item_container_category_news

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemContainerCategoryNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class ViewHolder(val binding: ItemContainerCategoryNewsBinding) :
        BaseViewHolder<CategoryNewsAdapter>(binding = binding) {
        private val ctx = binding.root.context

        init {
            binding.rvMenu.apply {
                layoutManager =
                    LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(
                    LinearSpacingItemDecoration(
                        ctx, com.intuit.sdp.R.dimen._9sdp, false
                    )
                )
            }
        }

        override fun bindData(data: CategoryNewsAdapter) {
            binding.adapter = data
            binding.executePendingBindings()
        }
    }
}