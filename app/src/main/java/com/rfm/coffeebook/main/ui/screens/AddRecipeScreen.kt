package com.rfm.coffeebook.main.ui.screens

import com.rfm.coffeebook.main.data.CoffeeRecipe
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rfm.coffeebook.CoffeeBookApplication
import com.rfm.coffeebook.main.viewmodel.CoffeeRecipeViewModelFactory
import com.rfm.coffeebook.ui.theme.CoffeeAccent

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

    var name by remember { mutableStateOf("") }
    var method by remember { mutableStateOf("") }
    var water by remember { mutableStateOf("") }
    var coffee by remember { mutableStateOf("") }
    var grind by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Nova receita", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome da receita") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CoffeeAccent,
                focusedLabelColor = CoffeeAccent
            )
        )

        OutlinedTextField(
            value = method,
            onValueChange = { method = it },
            label = { Text("Método (V60, Prensa...)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = water,
            onValueChange = { water = it },
            label = { Text("Água (ml)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = coffee,
            onValueChange = { coffee = it },
            label = { Text("Café (g)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = grind,
            onValueChange = { grind = it },
            label = { Text("Moagem") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Tempo de preparo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.addRecipe(
                CoffeeRecipe(
                    name = name,
                    method = method,
                    waterMl = water.toIntOrNull() ?: 0,
                    coffeeGrams = coffee.toIntOrNull() ?: 0,
                    grind = grind,
                    brewTime = time,
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
            Text("Salvar Receita", fontSize = 16.sp)
        }
    }
}
