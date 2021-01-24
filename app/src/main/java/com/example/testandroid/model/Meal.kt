package com.example.testandroid.model

import com.squareup.moshi.Json

data class Meal (
    @field:Json(name = "idMeal")
    val id: String = "",

    @field:Json(name = "strMeal")
    val name: String = "",

    @field:Json(name = "strMealThumb")
    val image: String = ""

)