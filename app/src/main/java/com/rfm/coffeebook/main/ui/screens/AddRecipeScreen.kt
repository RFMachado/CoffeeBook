package com.rfm.coffeebook.main.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfm.coffeebook.CoffeeBookApplication
import com.rfm.coffeebook.main.data.CoffeeRecipe
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModel
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModelFactory
import com.rfm.coffeebook.ui.theme.BackgroundAddRecipe
import com.rfm.coffeebook.ui.theme.BackgroundOutLine
import com.rfm.coffeebook.ui.theme.CoffeeAccent
import com.rfm.coffeebook.ui.theme.CoffeeLight
import com.rfm.coffeebook.ui.theme.CoffeeMedium
import com.rfm.coffeebook.ui.theme.TextDark
import com.rfm.coffeebook.ui.theme.TextLight
import com.rfm.coffeebook.ui.theme.TextTitleLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    onBack: () -> Unit
) {
    val application =
        LocalContext.current.applicationContext as CoffeeBookApplication

    val viewModel: CoffeeRecipeViewModel = viewModel(
        factory = CoffeeRecipeViewModelFactory(
            application.database.coffeeRecipeDao()
        )
    )

    // 📝 Estados dos campos
    var name by remember { mutableStateOf("") }
    var method by remember { mutableStateOf("") }
    var water by remember { mutableStateOf("") }
    var coffee by remember { mutableStateOf("") }
    var grind by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Nova Receita",
                        fontWeight = FontWeight.Bold,
                        color = TextTitleLight
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = TextTitleLight
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CoffeeMedium,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = TextLight
                )
            )
        },
        containerColor = CoffeeLight
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundAddRecipe)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            AddField("Nome da receita", name) { name = it }
            AddField("Método", method) { method = it }
            AddField("Água (ml)", water) { water = it }
            AddField("Café (g)", coffee) { coffee = it }
            AddField("Moagem", grind) { grind = it }
            AddField("Tempo de preparo", time) { time = it }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.addRecipe(
                        CoffeeRecipe(
                            method = method,
                            waterMl = water.toIntOrNull() ?: 0,
                            coffeeGrams = coffee.toIntOrNull() ?: 0,
                            grind = grind,
                            brewTime = time
                        )
                    )
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeeAccent
                )
            ) {
                Text(
                    "Salvar Receita",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}

@Composable
private fun AddField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BackgroundOutLine,    // 👈 fundo quando focado
            unfocusedContainerColor = BackgroundOutLine,  // 👈 fundo sem foco
            focusedTextColor = TextDark,       // 👈 texto digitado (focado)
            unfocusedTextColor = TextDark,
            unfocusedLabelColor = CoffeeMedium,   // 👈 cor do hint
            focusedLabelColor = CoffeeAccent, // quando focado
            unfocusedBorderColor = CoffeeMedium,
            focusedBorderColor = CoffeeAccent,
            cursorColor = CoffeeAccent
        )
    )
}


