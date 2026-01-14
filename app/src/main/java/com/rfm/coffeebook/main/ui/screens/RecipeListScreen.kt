package com.rfm.coffeebook.main.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfm.coffeebook.CoffeeBookApplication
import com.rfm.coffeebook.main.ui.components.CoffeeRecipeCard
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModel
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModelFactory
import com.rfm.coffeebook.ui.theme.CoffeeAccent
import com.rfm.coffeebook.ui.theme.CoffeeMedium
import com.rfm.coffeebook.ui.theme.TextTitleLight


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

    val recipes by viewModel.recipes.collectAsState()

    // 🧱 Scaffold
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CoffeeBook",
                        fontWeight = FontWeight.Bold,
                        color = TextTitleLight
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CoffeeMedium,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
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
                items(
                    items = recipes,
                    key = { it.id }
                ) { recipe ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = { value ->
                            if (value == SwipeToDismissBoxValue.EndToStart) {
                                viewModel.deleteRecipe(recipe)
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            DeleteBackground()
                        }
                    ) {
                        CoffeeRecipeCard(recipe = recipe)
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Excluir",
            tint = Color.White
        )
    }
}