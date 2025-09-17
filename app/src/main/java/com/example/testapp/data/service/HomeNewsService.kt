package com.example.testapp.data.service

import com.example.testapp.data.model.home_news.LatestNewsModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Andika Bratadirja
 * @date 15/09/2025
 */
interface HomeNewsService {
    @GET("/v2/top-headlines")
    suspend fun latestNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = ""
    ): LatestNewsModelResponse

    @GET("/v2/top-headlines")
    suspend fun categoryListNews(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = ""
    ): LatestNewsModelResponse

    @GET("/v2/top-headlines")
    suspend fun searchNews(
        @Query("q") keyword: String,
        @Query("apiKey") apiKey: String = ""
    ): LatestNewsModelResponse
}