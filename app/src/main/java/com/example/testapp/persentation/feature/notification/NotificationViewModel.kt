package com.example.testapp.persentation.feature.notification

import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.model.home_news.ui_state.LatestNewsModelUiState
import com.example.testapp.data.model.home_news.ui_state.NewsModelLocalUiState
import com.example.testapp.data.repository.home_news.NewsLocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class NotificationViewModel(
    private val newsLocalSource: NewsLocalSource
) : BaseViewModel() {
    private var tempNewsModelLocal: MutableList<NewsModelLocal> = mutableListOf()
    private val _observeNewsLocal = MutableStateFlow(NewsModelLocalUiState())
    val observeNewsLocal = _observeNewsLocal.asStateFlow()

    init {
        getNewsHasSaved()
    }

    private fun getNewsHasSaved() {
        CoroutineScope(Dispatchers.IO).launch {
            tempNewsModelLocal.clear()
            tempNewsModelLocal.addAll(
                newsLocalSource.getAllNews()
            )

            _observeNewsLocal.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    listNewsLocal = tempNewsModelLocal
                )
            }
        }
    }

}