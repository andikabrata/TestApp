package com.example.testapp.persentation.feature.home_news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.databinding.ActivityHomeNewsBinding
import com.example.testapp.persentation.feature.detail_news.DetailNewsActivity
import com.example.testapp.persentation.feature.home_news.adapter.CategoryListNewsAdapter
import com.example.testapp.persentation.feature.home_news.adapter.CategoryNewsAdapter
import com.example.testapp.persentation.feature.home_news.adapter.ContainerCategoryNewsAdapter
import com.example.testapp.persentation.feature.home_news.adapter.ContainerLatestNewsAdapter
import com.example.testapp.persentation.feature.home_news.adapter.LatestNewsAdapter
import com.example.testapp.persentation.feature.home_news.adapter.TitleAdapter
import com.example.testapp.persentation.feature.notification.NotificationActivity
import com.example.testapp.persentation.feature.search.SearchNewsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeNewsActivity : BaseActivity<HomeNewsViewModel>(), ViewDataBindingOwner<ActivityHomeNewsBinding>,
    HomeNewsView {
    override val layoutResourceId: Int = R.layout.activity_home_news
    override val viewModel: HomeNewsViewModel by viewModel()
    override var binding: ActivityHomeNewsBinding? = null

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var titleAdapter: TitleAdapter
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var categoryNewsAdapter: CategoryNewsAdapter
    private lateinit var categoryListNewsAdapter: CategoryListNewsAdapter

    private val isTitleAdapterInitialized
        get() = ::titleAdapter.isInitialized

    private val isLatestNewsAdapterInitialized
        get() = ::latestNewsAdapter.isInitialized

    private val isCategoryNewsAdapterInitialized
        get() = ::categoryNewsAdapter.isInitialized

    private val isCategoryListNewsAdapterInitialized
        get() = ::categoryListNewsAdapter.isInitialized

    companion object {
        fun startActivity(
            context: Context
        ) {
            val intent = Intent(context, HomeNewsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCountNotif()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar()
        setHandlingSearchView()
        setupInitializedAdapter()
        setupConcatAdapter()
        observeData()
    }

    private fun setToolBar() {
        binding?.layoutToolbar?.apply {
            ivNotification.setOnClickListener {
                goToNotification()
            }
        }
    }

    private fun setHandlingSearchView() {
        binding?.iclSearchView?.etSearch?.apply {
            isFocusable = false
            isFocusableInTouchMode = false
            isCursorVisible = false
            keyListener = null
            setOnClickListener {
                SearchNewsActivity.startActivity(this@HomeNewsActivity)
            }
        }
    }

    private fun setupInitializedAdapter() {
        setupTitleAdapter()
        setupLatestNewsAdapter()
        setupCategoryNewsAdapter()
        setupDetailCategoryNewsAdapter()
    }

    private fun setupTitleAdapter() {
        if (!isTitleAdapterInitialized) {
            titleAdapter = TitleAdapter("Latest News")
        }
    }

    private fun setupLatestNewsAdapter() {
        if (!isLatestNewsAdapterInitialized) {
            latestNewsAdapter = LatestNewsAdapter {
                goToDetailNews(latestNews = it)
            }
        }
    }

    private fun setupCategoryNewsAdapter() {
        if (!isCategoryNewsAdapterInitialized) {
            categoryNewsAdapter = CategoryNewsAdapter() {
                viewModel.getCategoryListNews(categoryName = it.title)
            }
        }
    }

    private fun setupDetailCategoryNewsAdapter() {
        if (!isCategoryListNewsAdapterInitialized) {
            categoryListNewsAdapter = CategoryListNewsAdapter {
                goToDetailNews(latestNews = it)
            }
        }
    }

    private fun setupConcatAdapter() {
        val config = ConcatAdapter.Config.Builder()
        config.setIsolateViewTypes(false)

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        manager.apply {
            recycleChildrenOnDetach = true
            isItemPrefetchEnabled = true
            initialPrefetchItemCount = 6
        }

        val containerLatestNewsAdapter = ContainerLatestNewsAdapter().apply {
            setData(latestNewsAdapter)
        }

        val containerCategoryNewsAdapter = ContainerCategoryNewsAdapter().apply {
            setData(categoryNewsAdapter)
        }

        concatAdapter = ConcatAdapter(
            config.build(),
            titleAdapter,
            containerLatestNewsAdapter,
            containerCategoryNewsAdapter,
            categoryListNewsAdapter
        )

        val sharedRecyclerViewPool = RecyclerView.RecycledViewPool()
        binding?.rvNewsFeed?.apply {
            layoutManager = manager
            adapter = concatAdapter
            clearOnScrollListeners()
            setRecycledViewPool(sharedRecyclerViewPool)
        }
    }

    private fun observeData() {
        observeGetCountNotif()
        observeGetLatestNews()
        observeGetCategoryNews()
        observeGetCategoryDetailNews()
    }

    private fun observeGetCountNotif() {
        observeData(viewModel.countNotif) { count ->
            if (count == 0) return@observeData
            binding?.layoutToolbar?.apply {
                ivBgCount.visibility = View.VISIBLE
                tvCount.visibility = View.VISIBLE
                tvCount.text = count.toString()
            }
        }
    }

    private fun observeGetLatestNews() {
        observeData(viewModel.responeGetLatestNews) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        latestNewsAdapter.submitList(it.model?.articles)
                        viewModel.getCategoryNews()
                    }

                    is Resource.Error -> {
                        when (it.errorData.code) {
                            429 -> Toast.makeText(this, "Token Expired", Toast.LENGTH_SHORT).show()
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun observeGetCategoryNews() {
        observeData(viewModel.responeGetCategoryNews) { result ->
            result?.let {
                categoryNewsAdapter.submitList(result)
            }
        }
    }

    private fun observeGetCategoryDetailNews() {
        observeData(viewModel.responeCategoryListNews) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        categoryListNewsAdapter.apply {
                            submitList(null) {
                                submitList(it.model?.articles)
                            }
                        }
                        viewModel.getCategoryNews()
                    }

                    is Resource.Error -> {}
                    else -> {}
                }
            }
        }
    }

    private fun goToDetailNews(latestNews: LatestNews) {
        DetailNewsActivity.startActivity(context = this, latestNews = latestNews)
    }

    private fun goToNotification() {
        NotificationActivity.startActivity(context = this)
    }
}