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
    suspend fun getAllCity(): Flow<DataState<List<CityItem>>> = flow {
        emit(DataState.Success(cityDB.getAll()))
    }

    suspend fun insertCities( city: List<CityItem>): List<Long> {
        return cityDB.insertAllCities(city)
    }


    suspend fun getAllFood(): Flow<DataState<List<FoodItem>>> = flow {
        emit(DataState.Success(foodDB.getAll()))
    }

    suspend fun insertAllFoods( foods: List<FoodItem>): List<Long> {
        return foodDB.insertAllFoods(foods)
    }

    suspend fun clearAllCities() {
        cityDB.clearAll()
    }

    suspend fun clearAllFoods() {
        foodDB.clearAll()
    }


}