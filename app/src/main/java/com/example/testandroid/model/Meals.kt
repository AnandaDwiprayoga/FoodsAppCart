package com.example.testandroid.model

import com.squareup.moshi.Json

data class Meals (
    @field:Json(name = "meals")
    val meals: List<Meal>
)