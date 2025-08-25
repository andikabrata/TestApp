package com.example.testapp.persentation.feature.search

import androidx.lifecycle.MutableLiveData
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.repository.home_news.NewsLocalSource
import com.example.testapp.domain.home_news.GetLatestNewsUseCase
import com.example.testapp.domain.search.GetSearchNewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 25/08/2025
 */
class SearchNewsViewModel(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getSearchNewsUseCase: GetSearchNewsUseCase,
    private val newsLocalSource: NewsLocalSource,
) : BaseViewModel() {
    var responeGetLatestNews = MutableLiveData<Resource<LatestNewsModel>>()
    private var tempNewsModelLocal: MutableList<NewsModelLocal> = mutableListOf()
    var countNotif = MutableLiveData(0)

    init {
        getLatestNews()
    }

    fun getCountNotif() {
        CoroutineScope(Dispatchers.IO).launch {
            tempNewsModelLocal.clear()
            tempNewsModelLocal.addAll(
                newsLocalSource.getAllNews()
            )
            countNotif.postValue(tempNewsModelLocal.size)
        }
    }

    private fun getLatestNews() {
        launch {
            getLatestNewsUseCase().collect {
                responeGetLatestNews.value = it
            }
        }
    }

    fun getSearchNews(kewyword: String) {
        launch {
            getSearchNewsUseCase(kewyword).collect {
                responeGetLatestNews.value = it
            }
        }
    }
}
