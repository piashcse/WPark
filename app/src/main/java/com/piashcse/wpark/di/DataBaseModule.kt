package com.piashcse.wpark.di

import android.content.Context
import androidx.room.Room
import com.piashcse.wpark.data.datasource.local.AppDatabase
import com.piashcse.wpark.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    /**
     * Provides AppDatabase
     */
    @Provides
    fun provideDB(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        AppConstants.LOCAL_DB_NAME
    ).build()

    /**
     * Provides cityDao an object to access city table from Database
     */
    @Provides
    fun provideCityDao(db: AppDatabase) = db.cityDao()

    /**
     * Provides foodDao an object to access food table from Database
     */
    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()
}