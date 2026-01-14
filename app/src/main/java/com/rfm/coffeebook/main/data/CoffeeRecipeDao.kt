package com.rfm.coffeebook.main.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeRecipeDao {

    @Query("SELECT * FROM coffee_recipe ORDER BY id DESC")
    fun getAllRecipes(): Flow<List<CoffeeRecipe>>

    @Insert
    suspend fun insert(recipe: CoffeeRecipe)

    @Delete
    suspend fun delete(recipe: CoffeeRecipe)
}