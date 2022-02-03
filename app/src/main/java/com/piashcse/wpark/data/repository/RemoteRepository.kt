package com.piashcse.wpark.data.repository

import com.piashcse.wpark.utils.network.DataState
import com.piashcse.wpark.data.datasource.remote.ApiService
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun cities(): Flow<DataState<List<CityItem>>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.cities()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun foods(): Flow<DataState<List<FoodItem>>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.foods()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}