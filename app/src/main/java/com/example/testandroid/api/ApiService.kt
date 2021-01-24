package com.example.testandroid.api

import com.example.testandroid.model.Meals
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("filter.php?c=Seafood")
    suspend fun getMeals() : Response<Meals>

}