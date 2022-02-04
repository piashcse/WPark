package com.piashcse.wpark.data.datasource.local.dao

import androidx.room.*
import com.piashcse.wpark.data.model.CityItem

@Dao
interface CityDao {
    /**
     *  Get all city from database
     */
    @Query("SELECT * FROM cityItem")
    suspend fun getAll(): List<CityItem>

    /**
     * Insert city into the database
     */
    @Insert
    suspend fun insertAll(vararg city: CityItem)

    /**
     * Insert city into the database as list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCities(articles: List<CityItem>): List<Long>

    /**
     * Delete city from the database
     */
    @Delete
    suspend fun delete(city: CityItem)

    /**
     * Delete all city from the database
     */
    @Query("DELETE FROM cityItem")
    suspend fun clearAll()
}