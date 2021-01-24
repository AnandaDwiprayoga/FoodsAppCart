package com.example.testandroid.room


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testandroid.model.MealDB

@Database(entities = [MealDB::class], version = 1, exportSchema = false)
abstract class MealsDatabase : RoomDatabase() {
    abstract fun mealsDao(): MealsDao
}