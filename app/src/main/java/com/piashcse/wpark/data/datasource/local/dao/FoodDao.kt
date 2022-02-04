package com.piashcse.wpark.data.datasource.local.dao

import androidx.room.*
import com.piashcse.wpark.data.model.FoodItem

@Dao
interface FoodDao {
    /**
     *  Get all food from database
     */
    @Query("SELECT * FROM foodItem")
    suspend fun getAll(): List<FoodItem>

    /**
     * Insert food into the database
     */
    @Insert
    suspend fun insertAll(vararg foods: FoodItem)

    /**
     * Insert food into the database as list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFoods(articles: List<FoodItem>): List<Long>

    /**
     * Delete food from the database
     */
    @Delete
    suspend fun delete(food: FoodItem)

    /**
     * Delete all food from the database
     */
    @Query("DELETE FROM foodItem")
    suspend fun clearAll()
}