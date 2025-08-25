package com.example.testapp.persentation.feature.home_news

import androidx.lifecycle.MutableLiveData
import com.example.testapp.core.base.network.Resource
import com.example.testapp.core.base.view.BaseViewModel
import com.example.testapp.data.model.home_news.CategoryNewsModel
import com.example.testapp.data.model.home_news.LatestNewsModel
import com.example.testapp.data.model.home_news.NewsModelLocal
import com.example.testapp.data.repository.home_news.NewsLocalSource
import com.example.testapp.domain.home_news.GetCategoryListNewsUseCase
import com.example.testapp.domain.home_news.GetLatestNewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class HomeNewsViewModel(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getCategoryListNewsUseCase: GetCategoryListNewsUseCase,
    private val newsLocalSource: NewsLocalSource,
) : BaseViewModel() {
    var responeGetLatestNews = MutableLiveData<Resource<LatestNewsModel>>()
    var responeGetCategoryNews = MutableLiveData<List<CategoryNewsModel>>()
    var responeCategoryListNews = MutableLiveData<Resource<LatestNewsModel>>()
    private var tempNewsModelLocal: MutableList<NewsModelLocal> = mutableListOf()
    var countNotif = MutableLiveData(0)

    init {
        getLatestNews()
        getCategoryListNews(categoryName = "Sport")
    }

    fun getCountNotif() {
        CoroutineScope(Dispatchers.IO).launch {
            tempNewsModelLocal.clear()
            tempNewsModelLocal.addAll(
                newsLocalSource.getAllNews()
            )
            countNotif.postValue(tempNewsModelLocal.size)
        }
    }

    private fun getLatestNews() {
        launch {
            getLatestNewsUseCase().collect {
                responeGetLatestNews.value = it
            }
        }
    }

    fun getCategoryNews() {
        launch {
            responeGetCategoryNews.value = initDataCategoryNews()
        }
    }

    fun getCategoryListNews(categoryName: String) {
        launch {
            getCategoryListNewsUseCase(parameters = categoryName.lowercase()).collect {
                responeCategoryListNews.value = it
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