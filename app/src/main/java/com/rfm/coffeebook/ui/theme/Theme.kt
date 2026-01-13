package com.rfm.coffeebook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CoffeeColorScheme = darkColorScheme(
    primary = CoffeeMedium,
    secondary = CoffeeAccent,
    background = CoffeeDark,
    surface = CoffeeDark,
    onPrimary = TextLight,
    onSecondary = TextLight,
    onBackground = TextLight,
    onSurface = TextLight
)

@Composable
fun CoffeeBookTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CoffeeColorScheme,
        typography = Typography(),
        content = content
    )
}