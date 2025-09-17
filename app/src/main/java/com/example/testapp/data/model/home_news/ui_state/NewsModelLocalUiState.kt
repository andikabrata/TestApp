package com.example.testapp.data.model.home_news.ui_state

import com.example.testapp.data.model.home_news.NewsModelLocal

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
data class NewsModelLocalUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val listNewsLocal: List<NewsModelLocal>? = emptyList()
)
