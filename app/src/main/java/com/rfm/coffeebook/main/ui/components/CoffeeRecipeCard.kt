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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfm.coffeebook.main.data.CoffeeRecipe
import com.rfm.coffeebook.ui.theme.CoffeeCard
import com.rfm.coffeebook.ui.theme.TextLight

@Composable
fun CoffeeRecipeCard(recipe: CoffeeRecipe) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(modifier = Modifier.padding(12.dp)) {
//            Text(recipe.name, fontWeight = FontWeight.Bold)
//            Text("Método: ${recipe.method}")
//            Text("Água: ${recipe.waterMl} ml")
//            Text("Café: ${recipe.coffeeGrams} g")
//            Text("Moagem: ${recipe.grind}")
//            Text("Tempo: ${recipe.brewTime}")
//        }
//    }

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
                recipe.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextLight
            )

            Spacer(Modifier.height(8.dp))

            Text("Método: ${recipe.method}")
            Text("Água: ${recipe.waterMl} ml • Café: ${recipe.coffeeGrams} g")
            Text("Moagem: ${recipe.grind}")
            Text("Tempo: ${recipe.brewTime}")
        }
    }
}
