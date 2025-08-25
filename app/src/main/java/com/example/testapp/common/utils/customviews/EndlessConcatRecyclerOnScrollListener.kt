package com.example.testapp.common.utils.customviews

import androidx.recyclerview.widget.RecyclerView

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
abstract class EndlessConcatRecyclerOnScrollListener(
    private val focusedAdapter: RecyclerView.Adapter<*>,
    private val mRecyclerView: RecyclerView,
    private val isVertical: Boolean? = false,
) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private var totalItemCount: Int = 0
    private var page = 1

    var currentPage: Int
        get() = page
        set(value) {
            page = value
        }

    var previousListSize: Int
        get() = previousTotal
        set(value) {
            previousTotal = value
        }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        totalItemCount = focusedAdapter.itemCount
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (isVertical == true) {
                    if (!loading) {
                        onLoadMore(currentPage = ++page)
                        loading = true
                    }
                } else {
                    if (!loading && !mRecyclerView.canScrollVertically(1)) {
                        onLoadMore(currentPage = ++page)
                        loading = true
                    }
                }
            }
        }
    }

    fun reset() {
        previousTotal = 0
        page = 1
        loading = true
    }

    abstract fun onLoadMore(currentPage: Int)
}