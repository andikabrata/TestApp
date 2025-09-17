package com.example.testapp.data.mapper

import com.example.testapp.core.base.mapper.BaseMapper
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.data.model.home_news.NewsModelLocal

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class NewsModelLocalToNewsLatestMapper : BaseMapper<NewsModelLocal, LatestNews>() {
    override fun map(value: NewsModelLocal): LatestNews {
        return LatestNews().apply {
            value.let {
                title = it.title
                author = it.author
                description = it.description
                urlToImage = it.url_to_image
                publishedAt = it.published_at
            }
        }
    }
}