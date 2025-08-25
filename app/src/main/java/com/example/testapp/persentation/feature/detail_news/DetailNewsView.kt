package com.example.testapp.persentation.feature.detail_news

import com.example.testapp.core.base.view.BaseView
import com.example.testapp.data.model.home_news.LatestNews

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
interface DetailNewsView : BaseView {
    fun onBtnSaveClicked(data: LatestNews)
}