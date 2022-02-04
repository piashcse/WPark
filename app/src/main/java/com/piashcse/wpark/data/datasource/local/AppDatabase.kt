package com.piashcse.wpark.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piashcse.wpark.data.datasource.local.dao.CityDao
import com.piashcse.wpark.data.datasource.local.dao.FoodDao
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem

/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(
    entities = [CityItem::class, FoodItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun foodDao(): FoodDao
}
