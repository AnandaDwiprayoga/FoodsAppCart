package com.example.testandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.testandroid.model.MealDB
import com.example.testandroid.repository.MainRepository
import kotlinx.coroutines.launch

class CartViewModel (
    private val mainRepository: MainRepository
): ViewModel(){

    val mealsFromDb: LiveData<List<MealDB>> = mainRepository.getMealsDb()

    fun deleteMealDb(mealDB: MealDB) = viewModelScope.launch {
        mainRepository.deleteMeal(mealDB)
    }

}