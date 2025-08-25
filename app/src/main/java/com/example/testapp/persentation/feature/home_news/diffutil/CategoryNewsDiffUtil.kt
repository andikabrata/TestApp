package com.example.testapp.persentation.feature.home_news.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.testapp.data.model.home_news.CategoryNewsModel

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class CategoryNewsDiffUtil : DiffUtil.ItemCallback<CategoryNewsModel>() {
    override fun areItemsTheSame(oldItem: CategoryNewsModel, newItem: CategoryNewsModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CategoryNewsModel, newItem: CategoryNewsModel): Boolean =
        oldItem == newItem
}