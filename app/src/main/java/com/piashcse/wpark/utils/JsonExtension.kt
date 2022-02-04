package com.piashcse.wpark.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.piashcse.wpark.ui.adapter.CityAdapter
import com.piashcse.wpark.ui.adapter.FoodAdapter

/**
 * Transform simple object to String with Gson
 */
inline fun <reified T : Any> T.toPrettyJson(): String = Gson().toJson(this, T::class.java)

/**
 * Transform String Json to Object
 */
inline fun <reified T : Any> String.fromPrettyJson(): T = Gson().fromJson(this, T::class.java)

/**
 * Transform String List Json to Object
 */
inline fun <reified T : Any> String.fromPrettyJsonList(): MutableList<T> =
    when (this.isNotEmpty()) {
        true -> Gson().fromJson(this, object : TypeToken<MutableList<T>>() {}.type)
        false -> mutableListOf()
    }