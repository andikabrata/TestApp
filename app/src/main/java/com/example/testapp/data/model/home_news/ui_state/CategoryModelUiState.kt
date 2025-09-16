package com.example.testapp.data.model.home_news.ui_state

import com.example.testapp.data.model.home_news.CategoryNewsModel

/**
 * @author Andika Bratadirja
 * @date 16/09/2025
 */
data class CategoryModelUiState (
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val listCategoryNews: List<CategoryNewsModel> = emptyList()
)