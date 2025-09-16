package com.example.testapp.persentation.feature.home_news

import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.ui_state.CategoryModelUiState
import com.example.testapp.data.model.home_news.CategoryNewsModel
import com.example.testapp.data.model.home_news.ui_state.LatestNewsModelUiState
import com.example.testapp.data.util.onFailure
import com.example.testapp.data.util.onLoading
import com.example.testapp.data.util.onSuccess
import com.example.testapp.domain.home_news.GetCategoryListNewsUseCase
import com.example.testapp.domain.home_news.GetLatestNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 13/09/2025
 */
class HomeNewsViewModel(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getCategoryListNewsUseCase: GetCategoryListNewsUseCase
) : BaseViewModel() {
    private val _observeLatestNews = MutableStateFlow(LatestNewsModelUiState())
    val observeLatestNews = _observeLatestNews.asStateFlow()
    private val _observeCategoryNews = MutableStateFlow(CategoryModelUiState())
    val observeCategoryNews = _observeCategoryNews.asStateFlow()
    private val _observeCategoryListNews = MutableStateFlow(LatestNewsModelUiState())
    val observeCategoryListNews = _observeCategoryListNews.asStateFlow()

    init {
        getLatestNews()
        getCategoryListNews(categoryName = "Sport")
    }

    private fun getLatestNews() {
        launch {
            getLatestNewsUseCase().collect { resource ->
                resource.onLoading {
                    _observeLatestNews.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                resource.onSuccess {
                    _observeLatestNews.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            listLatestNews = this?.articles
                        )
                    }
                    getCategoryNews()
                }
                resource.onFailure {
                    _observeLatestNews.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = this.message,
                            listLatestNews = null
                        )
                    }
                }
            }
        }
    }

    fun getCategoryNews() {
        launch {
            _observeCategoryNews.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    listCategoryNews = initDataCategoryNews()
                )
            }
        }
    }

    fun getCategoryListNews(categoryName: String) {
        launch {
            getCategoryListNewsUseCase(parameters = categoryName.lowercase()).collect { resource ->
                resource.onLoading {
                    _observeCategoryListNews.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                resource.onSuccess {
                    _observeCategoryListNews.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            listLatestNews = this?.articles
                        )
                    }
                    getCategoryNews()
                }
                resource.onFailure {
                    _observeCategoryListNews.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = this.message,
                            listLatestNews = null
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeNewsEvent) {
        when (event) {
            is HomeNewsEvent.OnCategoryClick -> {
                getCategoryListNews(event.categoryName)
            }
            HomeNewsEvent.OnRefresh -> {
                getLatestNews()
            }
        }
    }

    private fun initDataCategoryNews(): List<CategoryNewsModel> {
        val listCategory = arrayListOf<CategoryNewsModel>()
        listCategory.add(
            CategoryNewsModel(
                id = 1,
                title = "Sport"
            )
        )
        listCategory.add(
            CategoryNewsModel(
                id = 2,
                title = "General"
            )
        )
        listCategory.add(
            CategoryNewsModel(
                id = 3,
                title = "Health"
            )
        )
        listCategory.add(
            CategoryNewsModel(
                id = 4,
                title = "Science"
            )
        )
        listCategory.add(
            CategoryNewsModel(
                id = 5,
                title = "Entertainment"
            )
        )
        listCategory.add(
            CategoryNewsModel(
                id = 5,
                title = "Technology"
            )
        )
        return listCategory
    }
}