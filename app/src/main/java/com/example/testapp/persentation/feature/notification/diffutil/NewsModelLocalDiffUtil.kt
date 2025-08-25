package com.example.testapp.persentation.feature.notification.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.testapp.data.model.home_news.NewsModelLocal

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class NewsModelLocalDiffUtil : DiffUtil.ItemCallback<NewsModelLocal>() {
    override fun areItemsTheSame(oldItem: NewsModelLocal, newItem: NewsModelLocal): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: NewsModelLocal, newItem: NewsModelLocal): Boolean = oldItem == newItem
}