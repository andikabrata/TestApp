package com.example.testapp.data.model.room_entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.data.model.home_news.NewsModelLocal

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(history: NewsEntity)

    @Query("SELECT * from news_entity")
    fun getAllNewsHasSaved(): List<NewsModelLocal>

    @Query("DELETE from news_entity WHERE title = :title")
    fun deleteNews(title: String)
}