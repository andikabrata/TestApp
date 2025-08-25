package com.example.testapp.core.base.view

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.testapp.common.utils.Util

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
abstract class BaseListAdapter<T, VH : BaseViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffUtil) {
    abstract val isIncludeFooter: Boolean

    companion object {
        internal const val DATA_TYPE = 0
        internal const val FOOTER_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (isIncludeFooter) {
            if (position == currentList.size) {
                return FOOTER_TYPE
            }
        }
        return DATA_TYPE
    }

    override fun getItemCount(): Int = if (Util.isNotNull(currentList)) {
        if (isIncludeFooter) currentList.size + 1
        else currentList.size
    } else {
        0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        when (holder.itemViewType) {
            DATA_TYPE -> holder.bindData(getItem(position))
        }
    }
}