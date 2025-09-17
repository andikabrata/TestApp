package com.example.testapp.data.mapper

import com.example.testapp.core.base.mapper.BaseMapper
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.data.model.home_news.NewsModelLocal

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class LatestNewsToNewsModelLocalMapper : BaseMapper<LatestNews, NewsModelLocal>() {
    override fun map(value: LatestNews): NewsModelLocal {
        return NewsModelLocal().apply {
            value.let {
                title = it.title
                author = it.author
                description = it.description
                url_to_image = it.urlToImage
                published_at = it.publishedAt
            }
        }
    }
}