package com.example.testapp.domain.home_news

import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.usecase.FlowUseCase
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.repository.home_news.HomeNewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
class GetCategoryListNewsUseCase(
    private val repo: HomeNewsRepository
) : FlowUseCase<String, LatestNewsModel>() {
    override suspend fun execute(parameters: String?): Flow<Resource<LatestNewsModel>> {
        return repo.getCategoryListNews(category = parameters ?: "")
    }
}