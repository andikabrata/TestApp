package com.example.testapp.persentation.feature.detail_news

import androidx.lifecycle.MutableLiveData
import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.model.room_entity.NewsEntity
import com.example.testapp.data.repository.home_news.NewsLocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class DetailNewsViewModel(
    private val newsLocalSource: NewsLocalSource,
) : BaseViewModel() {
    var latestNews = MutableLiveData<LatestNews>()
    private var tempNewsModelLocal: MutableList<NewsModelLocal> = mutableListOf()
    var countNotif = MutableLiveData(0)
    var isFromNotif = false

    fun getCountNotif() {
        CoroutineScope(Dispatchers.IO).launch {
            tempNewsModelLocal.clear()
            tempNewsModelLocal.addAll(
                newsLocalSource.getAllNews()
            )
            countNotif.postValue(tempNewsModelLocal.size)
        }
    }

    fun saveNews(data: NewsModelLocal) {
        CoroutineScope(Dispatchers.IO).launch {
            val newsEntity = NewsEntity(
                title = data.title,
                author = data.author,
                description = data.description,
                url_to_image = data.url_to_image,
                published_at = data.published_at
            )
            newsLocalSource.saveNews(newsEntity = newsEntity)
            getCountNotif()
        }
    }
}