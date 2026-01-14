package com.rfm.coffeebook.main.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_recipe")
data class CoffeeRecipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val method: String,
    val waterMl: Int,
    val coffeeGrams: Int,
    val grind: String,
    val brewTime: String,
)