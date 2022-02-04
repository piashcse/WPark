package com.piashcse.wpark.data.datasource.remote

import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import retrofit2.http.GET
/**
 * Api service for remote server
 */
interface ApiService {
    @GET(ApiURL.CITIES)
    suspend fun cities(): List<CityItem>

    @GET(ApiURL.FOODS)
    suspend fun foods(): List<FoodItem>
}