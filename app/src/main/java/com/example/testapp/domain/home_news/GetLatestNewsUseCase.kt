package com.example.testapp.domain.home_news

import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.usecase.FlowUseCase
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.repository.home_news.HomeNewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class GetLatestNewsUseCase(
    private val repo: HomeNewsRepository
) : FlowUseCase<Unit, LatestNewsModel>() {
    override suspend fun execute(parameters: Unit?): Flow<Resource<LatestNewsModel>> {
        return repo.getLatestNews(country = "us")
    }
}