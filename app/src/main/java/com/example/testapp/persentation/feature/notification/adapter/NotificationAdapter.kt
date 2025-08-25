package com.example.testapp.persentation.feature.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testapp.core.base.view.BaseListAdapter
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.databinding.ItemNotificationBinding
import com.example.testapp.persentation.feature.notification.diffutil.NewsModelLocalDiffUtil

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class NotificationAdapter(
    private val onItemClick: (NewsModelLocal) -> Unit
) : BaseListAdapter<NewsModelLocal, NotificationAdapter.ViewHolder>(
    diffUtil = NewsModelLocalDiffUtil()
) {
    private var originalList: List<NewsModelLocal> = emptyList()

    override val isIncludeFooter: Boolean
        get() = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(val binding: ItemNotificationBinding) :
        BaseViewHolder<NewsModelLocal>(binding = binding) {
        override fun bindData(data: NewsModelLocal) {
            binding.apply {
                this.data = data
                executePendingBindings()
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }

    fun setData(list: List<NewsModelLocal>) {
        originalList = list
        submitList(list)
    }

    fun filter(keyword: String) {
        val filtered = if (keyword.isEmpty()) {
            originalList
        } else {
            originalList.filter {
                it.title.contains(keyword, ignoreCase = true) || it.description.contains(keyword, ignoreCase = true)
            }
        }
        submitList(filtered)
    }

}