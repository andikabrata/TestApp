package com.example.testapp.persentation.feature.detail_news

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.testapp.R
import com.example.testapp.core.base.view.BaseActivity
import com.example.testapp.core.base.view.ViewDataBindingOwner
import com.example.testapp.data.mapper.LatestNewsToNewsModelLocalMapper
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.databinding.ActivityDetailNewsBinding
import com.example.testapp.persentation.feature.notification.NotificationActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsActivity : BaseActivity<DetailNewsViewModel>(), ViewDataBindingOwner<ActivityDetailNewsBinding>,
    DetailNewsView {
    override val layoutResourceId: Int = R.layout.activity_detail_news
    override val viewModel: DetailNewsViewModel by viewModel()
    override var binding: ActivityDetailNewsBinding? = null

    private val latestNewsToNewsModelLocalMapper: LatestNewsToNewsModelLocalMapper by inject()

    companion object {
        fun startActivity(
            context: Context,
            latestNews: LatestNews,
            isFromNotif: Boolean = false
        ) {
            val intent = Intent(context, DetailNewsActivity::class.java).apply {
                putExtra("EXTRA_NEWS", latestNews)
                putExtra("IS_FROM_NOTIF", isFromNotif)
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
        setToolbar()
        viewModel.apply {
            latestNews.value = getParcelableExtra()
            isFromNotif = intent.getBooleanExtra("IS_FROM_NOTIF", false)
        }
        observeGetCountNotif()
    }

    private fun setToolbar() {
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

    private fun getParcelableExtra(): LatestNews? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_NEWS", LatestNews::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_NEWS")
        }
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

    override fun onBtnSaveClicked(data: LatestNews) {
        val newsModelLocal = latestNewsToNewsModelLocalMapper.map(data)
        viewModel.saveNews(newsModelLocal)
        Toast.makeText(this, "News Saved", Toast.LENGTH_SHORT).show()
    }

    private fun goToNotification() {
        NotificationActivity.startActivity(context = this)
    }
}