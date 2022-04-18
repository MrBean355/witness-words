package com.github.mrbean355.witnesswords.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordItem(
    text: String,
    onClick: () -> Unit
) {
    val fullWord = text.length == LETTER_COUNT
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold.takeIf { fullWord },
        modifier = Modifier
            .background(if (fullWord) MaterialTheme.colors.secondary else Color.Unspecified)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF
)
@Composable
fun WordItemPreview() {
    WordItem(text = "Hello world", onClick = {})
}