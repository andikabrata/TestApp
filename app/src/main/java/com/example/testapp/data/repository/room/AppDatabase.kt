package com.example.testapp.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.data.model.room_entity.NewsDao
import com.example.testapp.data.model.room_entity.NewsEntity

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */

@Database(
    version = 1,
    entities = [
        NewsEntity::class
    ],
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appdatabase.db"
            ).build()
    }
}