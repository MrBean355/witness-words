package com.github.mrbean355.witnesswords.core

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.github.mrbean355.witnesswords.R

@Composable
fun AppToolbar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = rememberVectorPainter(Icons.Default.ArrowBack), contentDescription = stringResource(R.string.back_button_description))
            }
        }
    )
}