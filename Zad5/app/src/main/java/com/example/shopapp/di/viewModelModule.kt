package com.example.shopapp.di

import com.example.shopapp.views.cart.CartViewModel
import com.example.shopapp.views.items.ItemsViewModel
import com.example.shopapp.views.items.ItemDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ItemsViewModel(get()) }
    viewModel { ItemDetailsViewModel(get()) }
    viewModel { CartViewModel(get()) }

}