package com.example.testapp.persentation.feature.home_news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.core.base.view.BaseViewHolder
import com.example.testapp.databinding.ItemTitleBinding

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class TitleAdapter(
    private var title: String?
) : RecyclerView.Adapter<BaseViewHolder<String>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> = ViewHolder(
        ItemTitleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemViewType(position: Int): Int = R.layout.item_title

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        title?.let { holder.bindData(it) }
    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(private val binding: ItemTitleBinding) :
        BaseViewHolder<String>(binding = binding) {
        override fun bindData(data: String) {
            binding.title = data
            binding.executePendingBindings()
        }

    }
}