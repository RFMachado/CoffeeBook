package com.rfm.coffeebook.main.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoffeeRecipe::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeRecipeDao(): CoffeeRecipeDao
}