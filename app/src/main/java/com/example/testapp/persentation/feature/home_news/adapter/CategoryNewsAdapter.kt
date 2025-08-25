package com.example.testapp.persentation.feature.home_news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapp.core.base.view.BaseListAdapter
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.data.model.home_news.CategoryNewsModel
import com.example.testapp.databinding.ItemCategoryBinding
import com.example.testapp.persentation.feature.home_news.diffutil.CategoryNewsDiffUtil

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class CategoryNewsAdapter(
    private val onItemClick: (CategoryNewsModel) -> Unit
) : BaseListAdapter<CategoryNewsModel, CategoryNewsAdapter.ViewHolder>(
    diffUtil = CategoryNewsDiffUtil()
) {
    private var selectedPosition = 0

    override val isIncludeFooter: Boolean
        get() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        BaseViewHolder<CategoryNewsModel>(binding = binding) {
        override fun bindData(data: CategoryNewsModel) {
            binding.apply {
                this.data = data
                tvCategory.isSelected = selectedPosition == bindingAdapterPosition
                executePendingBindings()
                root.setOnClickListener {
                    onItemClick.invoke(data)
                    val previous = selectedPosition
                    selectedPosition = bindingAdapterPosition
                    notifyItemChanged(previous)
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }
}