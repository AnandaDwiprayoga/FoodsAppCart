package com.example.testandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mealdb")
data class MealDB (
    @field:PrimaryKey
    val id: String,
    val name: String,
    val image: String
)