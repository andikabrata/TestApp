package com.example.testapp.persentation.feature.detail_news

import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.model.room_entity.NewsEntity
import com.example.testapp.data.repository.home_news.NewsLocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class DetailNewsViewModel(
    private val newsLocalSource: NewsLocalSource
) : BaseViewModel() {
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
        }
    }
}