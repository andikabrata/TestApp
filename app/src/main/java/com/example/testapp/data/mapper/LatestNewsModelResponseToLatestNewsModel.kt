package com.example.testapp.data.mapper

import com.example.testapp.core.base.mapper.BaseMapper
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.model.home_news.LatestNewsModelResponse
import com.example.testapp.data.model.home_news.Source

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
class LatestNewsModelResponseToLatestNewsModel : BaseMapper<LatestNewsModelResponse, LatestNewsModel>() {
    override fun map(value: LatestNewsModelResponse): LatestNewsModel {
        return LatestNewsModel().apply {
            code = if (value.status == "ok") 0 else 503
            status = value.status
            message = value.message
            totalResults = value.totalResults
            articles = value.articles?.map {
                LatestNews().apply {
                    source = it.source.let {
                        Source().apply {
                            id = it?.id ?: ""
                            name = it?.name ?: ""
                        }
                    }
                    author = if (!it.author.isNullOrEmpty()) it.author ?: "" else "author"
                    title = it.title ?: ""
                    description = it.description ?: ""
                    url = it.url ?: ""
                    urlToImage = it.urlToImage ?: ""
                    publishedAt = it.publishedAt ?: ""
                    content = it.content ?: ""
                }
            }
        }
    }
}