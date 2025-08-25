package com.example.testapp.data.repository.home_news

import com.example.testapp.core.base.network.NetworkResource
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.network.SchedulerProvider
import com.example.testapp.data.mapper.LatestNewsModelResponseToLatestNewsModel
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.service.HomeNewsService
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class HomeNewsRepositoryImpl(
    private val schedulerProvider: SchedulerProvider,
    private val homeNewsService: HomeNewsService,
    private val mapper: LatestNewsModelResponseToLatestNewsModel
) : HomeNewsRepository {
    override suspend fun getLatestNews(country: String): Flow<Resource<LatestNewsModel>> {
        return object : NetworkResource<LatestNewsModel>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): LatestNewsModel {
                val request = homeNewsService.latestNews(
                    country = country,
                    apiKey = "002f1a266eed4699ae235abf4b0b8b31"
                )
                return mapper.map(request)
            }

        }.asFlow()
    }

    override suspend fun getCategoryListNews(category: String): Flow<Resource<LatestNewsModel>> {
        return object : NetworkResource<LatestNewsModel>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): LatestNewsModel {
                val request = homeNewsService.categoryListNews(
                    category = category,
                    apiKey = "002f1a266eed4699ae235abf4b0b8b31"
                )
                return mapper.map(request)
            }

        }.asFlow()
    }

    override suspend fun getSearchNews(keyword: String): Flow<Resource<LatestNewsModel>> {
        return object : NetworkResource<LatestNewsModel>(
            schedulerProvider = schedulerProvider
        ) {
            override suspend fun remoteFetch(): LatestNewsModel {
                val request = homeNewsService.searchNews(
                    keyword = keyword,
                    apiKey = "002f1a266eed4699ae235abf4b0b8b31"
                )
                return mapper.map(request)
            }

        }.asFlow()
    }
}