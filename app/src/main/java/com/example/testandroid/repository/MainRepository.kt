package com.example.testandroid.repository

import com.example.testandroid.api.ApiHelper
import com.example.testandroid.model.MealDB
import com.example.testandroid.room.MealsDao

class MainRepository(private val apiHelper: ApiHelper, private val mealsDao: MealsDao) {
    suspend fun getMeals() = apiHelper.getMeals()

    suspend fun insertMeal(mealDB: MealDB) = mealsDao.insert(mealDB)

    suspend fun deleteMeal(mealDB: MealDB) = mealsDao.delete(mealDB)

    fun getMealsDb() = mealsDao.getAllMealsDb()
}