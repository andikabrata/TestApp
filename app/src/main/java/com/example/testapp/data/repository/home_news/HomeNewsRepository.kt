package com.example.testapp.data.repository.home_news

import com.example.testapp.core.base.network.Resource
import com.example.testapp.data.model.home_news.LatestNewsModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
interface HomeNewsRepository {
    @Throws(Exception::class)
    suspend fun getLatestNews(country: String): Flow<Resource<LatestNewsModel>>

    @Throws(Exception::class)
    suspend fun getCategoryListNews(category: String): Flow<Resource<LatestNewsModel>>

    @Throws(Exception::class)
    suspend fun getSearchNews(keyword: String): Flow<Resource<LatestNewsModel>>
}