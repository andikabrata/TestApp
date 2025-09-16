package com.example.testapp.persentation.feature.home_news

/**
 * @author Andika Bratadirja
 * @date 16/09/2025
 */
sealed class HomeNewsEvent {
    data class OnCategoryClick(val categoryName: String) : HomeNewsEvent()
    object OnRefresh : HomeNewsEvent()
    // bisa tambah event lain, misalnya search, retry, dsb.
}