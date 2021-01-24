package com.example.testandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testandroid.model.MealDB

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealDB: MealDB)

    @Delete
    suspend fun delete(mealDB: MealDB)

    @Query("SELECT * FROM mealdb")
    fun getAllMealsDb() : LiveData<List<MealDB>>

}