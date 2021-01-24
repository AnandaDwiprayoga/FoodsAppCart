package com.example.testandroid.di

import com.example.testandroid.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get(), get()) }
}