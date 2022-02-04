package com.piashcse.wpark.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * FoodItem data class for remote api
 * Food Item Entity for room db
 */
@Entity
data class FoodItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)