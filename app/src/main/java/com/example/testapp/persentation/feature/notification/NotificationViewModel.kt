package com.example.testapp.persentation.feature.notification

import androidx.lifecycle.MutableLiveData
import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.repository.home_news.NewsLocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class NotificationViewModel(
    private val newsLocalSource: NewsLocalSource,
) : BaseViewModel() {

    private var tempNewsModelLocal: MutableList<NewsModelLocal> = mutableListOf()
    var responseGetNews = MutableLiveData<List<NewsModelLocal>>()
    var isNewsEmpty = MutableLiveData(false)

    init {
        getNewsHasSaved()
    }

    private fun getNewsHasSaved() {
        CoroutineScope(Dispatchers.IO).launch {
            tempNewsModelLocal.clear()
            tempNewsModelLocal.addAll(
                newsLocalSource.getAllNews()
            )
        }
        responseGetNews.postValue(tempNewsModelLocal)
    }
}