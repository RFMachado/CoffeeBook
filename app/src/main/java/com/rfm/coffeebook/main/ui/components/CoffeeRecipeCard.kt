package com.rfm.coffeebook.main.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfm.coffeebook.main.data.CoffeeRecipe
import com.rfm.coffeebook.ui.theme.CoffeeBookTheme
import com.rfm.coffeebook.ui.theme.CoffeeCard
import com.rfm.coffeebook.ui.theme.TextLight

@Composable
fun CoffeeRecipeCard(recipe: CoffeeRecipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CoffeeCard
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recipe.method,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextLight
            )
            Spacer(Modifier.height(8.dp))

            Text("Água: ${recipe.waterMl} ml • Café: ${recipe.coffeeGrams} g")
            Text("Moagem: ${recipe.grind}")
            Text("Tempo: ${recipe.brewTime}")
        }
    }
}

private val previewRecipe = CoffeeRecipe(
    id = 1,
    method = "V60 Clássico",
    waterMl = 300,
    coffeeGrams = 20,
    grind = "Média",
    brewTime = "2:30"
)

@Preview(showBackground = true)
@Composable
fun CoffeeRecipeCardPreview() {
    CoffeeBookTheme {
        CoffeeRecipeCard(
            recipe = previewRecipe
        )
    }
}
