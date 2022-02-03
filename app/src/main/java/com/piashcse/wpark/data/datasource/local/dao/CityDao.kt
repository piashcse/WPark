package com.piashcse.wpark.data.datasource.local.dao

import androidx.room.*
import com.piashcse.wpark.data.model.CityItem

@Dao
interface CityDao {
    @Query("SELECT * FROM cityItem")
    suspend fun getAll(): List<CityItem>

    @Insert
    suspend fun insertAll(vararg city: CityItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCities(articles: List<CityItem>): List<Long>

    @Delete
    suspend fun delete(city: CityItem)

    @Query("DELETE FROM cityItem")
    suspend fun clearAll()
}