package com.github.mrbean355.witnesswords.core

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InvisibleTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    CompositionLocalProvider(LocalTextSelectionColors provides InvisibleTextSelection) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = InvisibleText,
            singleLine = true,
            cursorBrush = SolidColor(Color.Transparent),
            modifier = modifier,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )
    }
}

private val InvisibleText = TextStyle(Color.Transparent, 0.sp)
private val InvisibleTextSelection = TextSelectionColors(
    handleColor = Color.Transparent,
    backgroundColor = Color.Transparent
)