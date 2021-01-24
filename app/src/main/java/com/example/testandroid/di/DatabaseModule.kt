package com.example.testandroid.di

import androidx.room.Room
import com.example.testandroid.room.MealsDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), MealsDatabase::class.java, "mealdb").build() }
    single { get<MealsDatabase>().mealsDao() }
}