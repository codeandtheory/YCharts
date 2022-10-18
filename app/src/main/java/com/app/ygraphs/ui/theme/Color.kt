package com.app.ygraphs.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class YChartsColors(
    background: Color,
    primary: Color,
    text: Color,
    button: Color
) {

    var background by mutableStateOf(background)
        private set
    var primary by mutableStateOf(primary)
        private set
    var text by mutableStateOf(text)
        private set
    var button by mutableStateOf(button)
        private set
}

fun lightColors(
    background: Color = Color.White,
    primary: Color = Color.White,
    text: Color = Color.White,
    button: Color = DarkGrey
): YChartsColors = YChartsColors(
    background,
    primary,
    text,
    button
)

fun darkColors(
    background: Color = DarkGrey,
    primary: Color = DarkGrey,
    text: Color = DarkGrey,
    button: Color = Color.White
): YChartsColors = YChartsColors(
    background,
    primary,
    text,
    button
)

val DarkGrey = Color(0xFF0F0F0F)

val LocalColors = staticCompositionLocalOf { lightColors() }
