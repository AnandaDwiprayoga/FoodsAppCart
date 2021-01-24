package com.example.testandroid.api

import com.example.testandroid.model.Meals
import retrofit2.Response

class ApiHelperImplement(private val apiService: ApiService) : ApiHelper {
    override suspend fun getMeals(): Response<Meals> = apiService.getMeals()

}