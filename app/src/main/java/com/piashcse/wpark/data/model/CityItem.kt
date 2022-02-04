package com.piashcse.wpark.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * CityItem data class for remote api
 * City Item Entity for room db
 */
@Entity
data class CityItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)