package com.piashcse.wpark.di

import com.piashcse.wpark.data.datasource.local.dao.CityDao
import com.piashcse.wpark.data.datasource.local.dao.FoodDao
import com.piashcse.wpark.data.datasource.remote.ApiService
import com.piashcse.wpark.data.repository.RemoteRepository
import com.piashcse.wpark.data.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Provides
    fun provideRemoteDataRepository(apiService: ApiService): RemoteRepository {
        return RemoteRepository(apiService)
    }

    /**
     * Provides DataBaseRepository for access room database basic methos
     */
    @Provides
    fun provideDataBaseRepository(cityDB: CityDao, foodDB: FoodDao): DatabaseRepository {
        return DatabaseRepository(cityDB, foodDB)
    }
}