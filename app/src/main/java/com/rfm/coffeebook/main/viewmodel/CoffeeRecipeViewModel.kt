package com.rfm.coffeebook.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rfm.coffeebook.main.data.CoffeeRecipe
import com.rfm.coffeebook.main.data.CoffeeRecipeDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoffeeRecipeViewModel(
    private val dao: CoffeeRecipeDao
) : ViewModel() {

    val recipes = dao.getAllRecipes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addRecipe(recipe: CoffeeRecipe) {
        viewModelScope.launch {
            dao.insert(recipe)
        }
    }

    fun deleteRecipe(recipe: CoffeeRecipe) {
        viewModelScope.launch {
            dao.delete(recipe)
        }
    }
}