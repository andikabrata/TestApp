package com.example.testapp.data.model.home_news

import android.os.Parcelable
import com.example.testapp.core.base.model.BaseResponse
import kotlinx.parcelize.Parcelize

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class LatestNewsModel() : BaseResponse() {
    var articles: List<LatestNews>? = null
}

@Parcelize
data class LatestNews(
    var source: Source? = null,
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String = "",
    var publishedAt: String = "",
    var content: String = "",
) : Parcelable

@Parcelize
data class Source(
    var id: String = "",
    var name: String = ""
) : Parcelable