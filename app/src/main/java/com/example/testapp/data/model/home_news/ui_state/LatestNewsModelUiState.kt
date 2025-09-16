package com.example.testapp.data.model.home_news.ui_state

import com.example.testapp.data.model.home_news.LatestNews

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
data class LatestNewsModelUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val listLatestNews: List<LatestNews>? = emptyList(),
)
