package com.scapricorne.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = LightPink,
    primaryVariant = DarkPink,
    secondary = LightBlue,
    secondaryVariant = DarkBlue,
    surface = LightGray,
    onSurface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

@Composable
fun DrinkDiscoveryTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        shapes = Shapes,
        content = content,
        typography = Typography
    )
}