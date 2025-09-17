package com.example.testapp.data.model.room_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
@Entity(tableName = "news_entity")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "url_to_image") var url_to_image: String = "",
    @ColumnInfo(name = "published_at") var published_at: String = "",
)
