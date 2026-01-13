package com.rfm.coffeebook.main.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfm.coffeebook.CoffeeBookApplication
import com.rfm.coffeebook.main.ui.components.CoffeeRecipeCard
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModel
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModelFactory
import com.rfm.coffeebook.ui.theme.CoffeeAccent
import com.rfm.coffeebook.ui.theme.CoffeeMedium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    onAddClick: () -> Unit
) {
    val application =
        LocalContext.current.applicationContext as CoffeeBookApplication

    val viewModel: CoffeeRecipeViewModel = viewModel(
        factory = CoffeeRecipeViewModelFactory(
            application.database.coffeeRecipeDao()
        )
    )

/*    val viewModel: CoffeeRecipeViewModel = viewModel()*/
    val recipes by viewModel.recipes.collectAsState()

    // 🧱 Scaffold
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CoffeeBook",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CoffeeMedium,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = CoffeeAccent
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar receita"
                )
            }
        }
    ) { paddingValues ->

        // 📜 Conteúdo da tela
        if (recipes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Nenhuma receita salva ☕",
                    modifier = Modifier.align(androidx.compose.ui.Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(recipes) { recipe ->
                    CoffeeRecipeCard(recipe = recipe)
                }
            }
        }
    }
}