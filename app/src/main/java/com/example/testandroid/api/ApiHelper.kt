package com.example.testandroid.api

import com.example.testandroid.model.Meals
import retrofit2.Response

interface ApiHelper {
    suspend fun getMeals() : Response<Meals>
}