package com.example.testapp.data.repository.home_news

import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.model.room_entity.NewsEntity

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
interface NewsLocalSource {
    suspend fun getAllNews(): List<NewsModelLocal>
    suspend fun saveNews(newsEntity: NewsEntity)
    suspend fun deleteNewsByTitle(title: String)
}