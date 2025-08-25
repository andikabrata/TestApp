package com.example.testapp.data.repository.room

import androidx.room.TypeConverter
import com.example.testapp.data.model.home_news.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class Converters {
    @TypeConverter
    fun fromArticles(value: List<Source>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toArticles(value: String): List<Source>? {
        val type = object : TypeToken<List<Source>>() {}.type
        return Gson().fromJson(value, type)
    }
}