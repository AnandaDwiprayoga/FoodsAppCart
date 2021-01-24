package com.example.testandroid.di

import com.example.testandroid.view_model.CartViewModel
import com.example.testandroid.view_model.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CartViewModel(get()) }
}