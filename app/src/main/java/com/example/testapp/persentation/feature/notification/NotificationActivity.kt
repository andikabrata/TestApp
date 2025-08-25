package com.example.testapp.persentation.feature.notification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.data.mapper.NewsModelLocalToNewsLatestMapper
import com.example.testapp.databinding.ActivityNotificationBinding
import com.example.testapp.persentation.feature.detail_news.DetailNewsActivity
import com.example.testapp.persentation.feature.notification.adapter.NotificationAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationActivity : BaseActivity<NotificationViewModel>(), ViewDataBindingOwner<ActivityNotificationBinding>,
    NotificationView {
    override val layoutResourceId: Int = R.layout.activity_notification
    override val viewModel: NotificationViewModel by viewModel()
    override var binding: ActivityNotificationBinding? = null

    private val newsModelLocalToNewsLatestMapper: NewsModelLocalToNewsLatestMapper by inject()
    private lateinit var notificationAdapter: NotificationAdapter

    private val isNotificationAdapterInitialized
        get() = ::notificationAdapter.isInitialized

    companion object {
        fun startActivity(
            context: Context
        ) {
            val intent = Intent(context, NotificationActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        setOnSearchActionListener()
        setupAdapter()
        observeGetNews()
    }

    private fun setToolbar() {
        binding?.iclToolbar?.apply {
            ivArrowBack.visibility = View.VISIBLE
            ivFilter.visibility = View.GONE
            ivNotification.visibility = View.GONE
            ivArrowBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun setOnSearchActionListener() {
        binding?.iclSearchView?.etSearch?.addTextChangedListener { editable ->
            val query = editable?.toString().orEmpty()
            notificationAdapter.filter(query)
        }
    }

    private fun setupAdapter() {
        if (!isNotificationAdapterInitialized) {
            notificationAdapter = NotificationAdapter {
                val data = newsModelLocalToNewsLatestMapper.map(it)
                DetailNewsActivity.startActivity(context = this, latestNews = data, isFromNotif = true)
            }
        }

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        manager.apply {
            recycleChildrenOnDetach = true
            isItemPrefetchEnabled = true
            initialPrefetchItemCount = 6
        }

        val sharedRecyclerViewPool = RecyclerView.RecycledViewPool()
        binding?.rvNotification?.apply {
            layoutManager = manager
            adapter = notificationAdapter
            clearOnScrollListeners()
            setRecycledViewPool(sharedRecyclerViewPool)
        }
    }

    private fun observeGetNews() {
        observeData(viewModel.responseGetNews) { result ->
            result?.let {
                if (result.isEmpty()) {
                    viewModel.isNewsEmpty.value = true
                } else {
                    viewModel.isNewsEmpty.value = false
                    notificationAdapter.setData(result)
                }
            }
        }
    }
}