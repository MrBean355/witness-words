package com.github.mrbean355.witnesswords.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Letter(
    text: String,
    modifier: Modifier = Modifier,
    big: Boolean = false
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        fontSize = if (big) 32.sp else 22.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF
)
@Composable
private fun LetterPreview() {
    Letter(text = "A")
}