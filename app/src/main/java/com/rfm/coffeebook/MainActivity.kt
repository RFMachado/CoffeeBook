package com.rfm.coffeebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rfm.coffeebook.main.ui.screens.AddRecipeScreen
import com.rfm.coffeebook.main.ui.screens.RecipeListScreen
import com.rfm.coffeebook.ui.theme.CoffeeBookTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoffeeBookApp()
        }
    }
}

@Composable
fun CoffeeBookApp() {
    CoffeeBookTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CoffeeBookNavHost()
        }
    }
}

@Composable
fun CoffeeBookNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "recipe_list"
    ) {
        composable("recipe_list") {
            RecipeListScreen(
                onAddClick = {
                    navController.navigate("add_recipe")
                }
            )
        }

        composable("add_recipe") {
            AddRecipeScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}


