package com.example.testapp.persentation.feature.home_news.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.testapp.data.model.home_news.LatestNews

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class LatestNewsDiffUtil : DiffUtil.ItemCallback<LatestNews>() {
    override fun areItemsTheSame(oldItem: LatestNews, newItem: LatestNews): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: LatestNews, newItem: LatestNews): Boolean = oldItem == newItem
}