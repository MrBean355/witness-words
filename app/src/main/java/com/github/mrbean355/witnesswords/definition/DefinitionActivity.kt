package com.github.mrbean355.witnesswords.definition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mrbean355.witnesswords.R
import com.github.mrbean355.witnesswords.core.AppToolbar
import com.github.mrbean355.witnesswords.theme.WitnessWordsTheme

private const val KEY_WORD = "WORD"

class DefinitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WitnessWordsTheme {
                Scaffold(
                    topBar = { AppToolbar(title = stringResource(R.string.definition_title), onBackClick = ::finish) }
                ) {
                    DefinitionScreen(word = intent.getStringExtra(KEY_WORD).orEmpty())
                }
            }
        }
    }
}

@Suppress("FunctionName")
fun DefinitionActivity(context: Context, word: String): Intent {
    return Intent(context, DefinitionActivity::class.java)
        .putExtra(KEY_WORD, word)
}

@Composable
fun DefinitionScreen(
    word: String,
    viewModel: DefinitionViewModel = viewModel()
) {
    val definitions by viewModel.definitions.collectAsState()

    Column {
        Text(
            text = word,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
        )
        LazyColumn {
            items(definitions) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = it.type.uppercase(), style = MaterialTheme.typography.body1)
                    Text(text = it.detail, style = MaterialTheme.typography.body2)
                }
                Divider()
            }
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.initialise(word)
    }
}