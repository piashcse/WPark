package com.piashcse.wpark.ui

import android.content.Context
import androidx.lifecycle.*
import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import com.piashcse.wpark.data.repository.RemoteRepository
import com.piashcse.wpark.data.repository.DatabaseRepository
import com.piashcse.wpark.utils.isConnected
import com.piashcse.wpark.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val applicationContext: Context,
    private val repo: RemoteRepository,
    private val db: DatabaseRepository,
) : ViewModel() {

    private val _foodResponse: MutableLiveData<DataState<List<FoodItem>>> =
        MutableLiveData()
    val foodResponse: LiveData<DataState<List<FoodItem>>>
        get() = _foodResponse

    private val _cityResponse: MutableLiveData<DataState<List<CityItem>>> =
        MutableLiveData()
    val cityResponse: LiveData<DataState<List<CityItem>>>
        get() = _cityResponse

    fun cities() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.cities().onEach {
                _cityResponse.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun foods() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.foods().onEach {
                _foodResponse.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun cityAndFood() {
        if (isConnected(applicationContext)) {
            viewModelScope.launch {
                repo.cities().zip(repo.foods()) { city, food ->
                    _cityResponse.value = city
                    _foodResponse.value = food
                    dbClearAndInsertion()
                    if (city is DataState.Success) {
                        db.insertCities(city.data)
                    }
                    if (food is DataState.Success) {
                        db.insertAllFoods(food.data)
                    }
                }.launchIn(viewModelScope)
            }
        } else {
            viewModelScope.launch {
                db.getAllCity().zip(db.getAllFood()) { city, food ->
                    _cityResponse.value = city
                    _foodResponse.value = food
                }.launchIn(viewModelScope)
            }
        }
    }

    private suspend fun dbClearAndInsertion() {
        db.clearAllCities()
        db.clearAllFoods()
    }
}