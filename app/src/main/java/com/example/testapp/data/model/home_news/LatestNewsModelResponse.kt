package com.example.testapp.data.model.home_news

import com.example.testapp.core.base.model.BaseApiResponse

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class LatestNewsModelResponse() : BaseApiResponse() {
    var articles: List<LatestNewsResponse>? = null
}

data class LatestNewsResponse(
    var source: SourceResponse? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null,
)

data class SourceResponse(
    var id: String? = null,
    var name: String? = null
)