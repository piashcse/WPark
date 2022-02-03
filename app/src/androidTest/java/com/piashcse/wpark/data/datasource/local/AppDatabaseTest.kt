package com.piashcse.wpark.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.piashcse.wpark.data.datasource.local.dao.CityDao
import com.piashcse.wpark.data.model.CityItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat
import com.piashcse.wpark.data.datasource.local.dao.FoodDao
import com.piashcse.wpark.data.model.FoodItem

@RunWith(JUnit4::class)
class AppDatabaseTest {
    private lateinit var db: AppDatabase
    private lateinit var cityDao: CityDao
    private lateinit var foodDao: FoodDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        cityDao = db.cityDao()
        foodDao = db.foodDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadCity() = runBlocking {
        val cityItem = CityItem(
            id = 1,
            name = "Tokyo",
            description = "Capital city of japan",
            image = "www.google.com"
        )
        cityDao.insertAll(cityItem)

        val cityItemFromDB = cityDao.getAll()
        assertThat(cityItemFromDB.contains(cityItem)).isTrue()
    }

    @Test
    fun writeAndDeleteCity() = runBlocking {
        val cityItem = CityItem(
            id = 1,
            name = "Tokyo",
            description = "Capital city of japan",
            image = "www.google.com"
        )
        cityDao.insertAll(cityItem)
        cityDao.delete(cityItem)

        val cityItemFromDB = cityDao.getAll()
        assertThat(cityItemFromDB.contains(cityItem)).isFalse()
    }

    @Test
    fun writeAndReadFood() = runBlocking {
        val foodItem = FoodItem(
            id = 1,
            name = "Tomato",
            image = "www.google.com"
        )
        foodDao.insertAll(foodItem)

        val foodItemFromDB = foodDao.getAll()
        assertThat(foodItemFromDB.contains(foodItem)).isTrue()
    }

    @Test
    fun writeAndDeleteFood() = runBlocking {
        val foodItem = FoodItem(
            id = 1,
            name = "Tomato",
            image = "www.google.com"
        )
        foodDao.insertAll(foodItem)
        foodDao.delete(foodItem)

        val foodItemFromDB = foodDao.getAll()
        assertThat(foodItemFromDB.contains(foodItem)).isFalse()
    }
}