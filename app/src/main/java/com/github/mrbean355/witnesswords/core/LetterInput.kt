package com.github.mrbean355.witnesswords.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LetterInput(
    letters: String,
    onLettersChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .then(modifier)
    ) {
        InvisibleTextField(
            text = letters,
            onValueChange = {
                onLettersChange(it.filter(Char::isLetter).take(LETTER_COUNT).uppercase())
            },
            modifier = Modifier.fillMaxSize()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Letter(
                    text = letters.at(0),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(1f)
            ) {
                Letter(text = letters.at(1))
                Divider(color = Color.Black, thickness = 2.dp)
                Letter(text = letters.at(5))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(1f)
            ) {
                Letter(text = letters.at(2))
                Divider(color = Color.Black, thickness = 2.dp)
                Letter(text = letters.at(6))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(1f)
            ) {
                Letter(text = letters.at(3))
                Divider(color = Color.Black, thickness = 2.dp)
                Letter(text = letters.at(7))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .weight(1f)
            ) {
                Letter(text = letters.at(4))
                Divider(color = Color.Black, thickness = 2.dp)
                Letter(text = letters.at(8))
            }
        }
    }
}

private fun String.at(pos: Int): String {
    return if (pos == length) {
        "_"
    } else {
        getOrNull(pos)?.toString() ?: " "
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF
)
@Composable
private fun LetterInputPreview() {
    LetterInput("ABCDEFGHI", onLettersChange = {})
}