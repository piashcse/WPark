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
    @Provides
    fun provideDB(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        AppConstants.LOCAL_DB_NAME
    ).build()

    @Provides
    fun provideCityDao(db: AppDatabase) = db.cityDao()

    @Provides
    fun provideFoodDao(db: AppDatabase) = db.foodDao()
}