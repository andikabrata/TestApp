package com.example.testapp.persentation.feature.search

import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.ui_state.LatestNewsModelUiState
import com.example.testapp.data.repository.home_news.NewsLocalSource
import com.example.testapp.data.util.onFailure
import com.example.testapp.data.util.onLoading
import com.example.testapp.data.util.onSuccess
import com.example.testapp.domain.home_news.GetLatestNewsUseCase
import com.example.testapp.domain.search.GetSearchNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class SearchNewsViewModel(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getSearchNewsUseCase: GetSearchNewsUseCase,
    private val newsLocalSource: NewsLocalSource,
) : BaseViewModel() {
    private val _observeLatestNews = MutableStateFlow(LatestNewsModelUiState())
    val observeLatestNews = _observeLatestNews.asStateFlow()

    init {
        getLatestNews()
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

    fun getSearchNews(kewyword: String) {
        launch {
            getSearchNewsUseCase(kewyword).collect { resource ->
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
}