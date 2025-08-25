package com.example.testapp.data.repository.home_news

import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.model.room_entity.NewsDao
import com.example.testapp.data.model.room_entity.NewsEntity

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class NewsLocalSourceImpl(
    private val newsDao: NewsDao
) : NewsLocalSource {
    override suspend fun getAllNews(): List<NewsModelLocal> {
        return newsDao.getAllNewsHasSaved()
    }

    override suspend fun saveNews(newsEntity: NewsEntity) {
        return newsDao.insertNews(newsEntity)
    }

    override suspend fun deleteNewsByTitle(title: String) {
        return newsDao.deleteNews(title)
    }
}