package com.rfm.coffeebook.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rfm.coffeebook.main.data.CoffeeRecipeDao

class CoffeeRecipeViewModelFactory(
    private val dao: CoffeeRecipeDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoffeeRecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoffeeRecipeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
