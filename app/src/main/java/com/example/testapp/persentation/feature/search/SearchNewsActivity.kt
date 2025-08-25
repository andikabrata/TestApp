package com.example.testapp.persentation.feature.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.databinding.ActivitySearchNewsBinding
import com.example.testapp.persentation.feature.detail_news.DetailNewsActivity
import com.example.testapp.persentation.feature.home_news.adapter.CategoryListNewsAdapter
import com.example.testapp.persentation.feature.notification.NotificationActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchNewsActivity : BaseActivity<SearchNewsViewModel>(), ViewDataBindingOwner<ActivitySearchNewsBinding>,
    SearchNewsView {
    override val layoutResourceId: Int = R.layout.activity_search_news
    override val viewModel: SearchNewsViewModel by viewModel()
    override var binding: ActivitySearchNewsBinding? = null

    private lateinit var categoryListNewsAdapter: CategoryListNewsAdapter

    private val isCategoryListNewsAdapterInitialized
        get() = ::categoryListNewsAdapter.isInitialized

    companion object {
        fun startActivity(
            context: Context
        ) {
            val intent = Intent(context, SearchNewsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
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
        setOnSearchActionListener()
        setupAdapter()
        observeData()
    }

    private fun setToolBar() {
        binding?.layoutToolbar?.apply {
            ivArrowBack.visibility = View.VISIBLE
            ivFilter.visibility = View.GONE
            ivArrowBack.setOnClickListener {
                finish()
            }
            ivNotification.setOnClickListener {
                goToNotification()
            }
        }
    }

    private fun setOnSearchActionListener() {
        binding?.iclSearchView?.etSearch?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                val query = binding?.iclSearchView?.etSearch?.text.toString()
                if (query.isNotBlank()) {
                    viewModel.getSearchNews(kewyword = query)
                }
                val imm =
                    binding?.iclSearchView?.etSearch?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding?.iclSearchView?.etSearch?.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    private fun setupAdapter() {
        if (!isCategoryListNewsAdapterInitialized) {
            categoryListNewsAdapter = CategoryListNewsAdapter {
                goToDetailNews(latestNews = it)
            }
        }

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        manager.apply {
            recycleChildrenOnDetach = true
            isItemPrefetchEnabled = true
            initialPrefetchItemCount = 6
        }

        val sharedRecyclerViewPool = RecyclerView.RecycledViewPool()
        binding?.rvNews?.apply {
            layoutManager = manager
            adapter = categoryListNewsAdapter
            clearOnScrollListeners()
            setRecycledViewPool(sharedRecyclerViewPool)
        }
    }

    private fun observeData() {
        observeGetCountNotif()
        observeGetLatestNews()
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
                        categoryListNewsAdapter.submitList(it.model?.articles)
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

    private fun goToNotification() {
        NotificationActivity.startActivity(context = this)
    }

    private fun goToDetailNews(latestNews: LatestNews) {
        DetailNewsActivity.startActivity(context = this, latestNews = latestNews)
    }
}