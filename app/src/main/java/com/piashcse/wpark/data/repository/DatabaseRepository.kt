package com.piashcse.wpark.data.repository

import com.piashcse.wpark.data.datasource.local.dao.CityDao
import com.piashcse.wpark.data.datasource.local.dao.FoodDao
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import com.piashcse.wpark.utils.network.DataState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val cityDB: CityDao,
    private val foodDB: FoodDao
) {
    /**
     * Get all the city data from room db and make it flow
     * @see [CityDao]
     */
    suspend fun getAllCity(): Flow<DataState<List<CityItem>>> = flow {
        emit(DataState.Success(cityDB.getAll()))
    }

    /**
     * Insert city into room db as list
     * @see [CityDao]
     */
    suspend fun insertCities(city: List<CityItem>): List<Long> {
        return cityDB.insertAllCities(city)
    }

    /**
     * Get all the food data from room db and make it flow
     * @see [FoodDao]
     */
    suspend fun getAllFood(): Flow<DataState<List<FoodItem>>> = flow {
        emit(DataState.Success(foodDB.getAll()))
    }

    /**
     * Insert food into room db as list
     * @see [FoodDao]
     */
    suspend fun insertAllFoods(foods: List<FoodItem>): List<Long> {
        return foodDB.insertAllFoods(foods)
    }

    /**
     * Delete all the data from city column
     * @see [CityDao]
     */
    suspend fun clearAllCities() {
        cityDB.clearAll()
    }

    /**
     * Delete all the data from food column
     *@see [FoodDao]
     */
    suspend fun clearAllFoods() {
        foodDB.clearAll()
    }


}