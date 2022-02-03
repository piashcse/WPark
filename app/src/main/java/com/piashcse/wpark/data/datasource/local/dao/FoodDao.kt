package com.piashcse.wpark.data.datasource.local.dao

import androidx.room.*
import com.piashcse.wpark.data.model.FoodItem

@Dao
interface FoodDao {
    @Query("SELECT * FROM fooditem")
    suspend fun getAll(): List<FoodItem>

    @Insert
    suspend fun insertAll(vararg foods: FoodItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFoods(articles: List<FoodItem>): List<Long>

    @Delete
    suspend fun delete(food: FoodItem)

    @Query("DELETE FROM foodItem")
    suspend fun clearAll()
}