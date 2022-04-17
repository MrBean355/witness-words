package com.github.mrbean355.witnesswords.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WitnessWordsTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColours) {
        StatusBar()
        content()
    }
}

@Composable
fun StatusBar() {
    val controller = rememberSystemUiController()
    val colour = MaterialTheme.colors.primaryVariant

    SideEffect {
        controller.setStatusBarColor(colour)
    }
}

private val LightColours = lightColors(
    primary = Color(0xFFDA0019),
    primaryVariant = Color(0xFF980011),
    secondary = Color(0xFFFFEB3B),
    secondaryVariant = Color(0xFFFFEB3B),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)