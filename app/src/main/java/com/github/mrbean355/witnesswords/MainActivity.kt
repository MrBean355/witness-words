package com.github.mrbean355.witnesswords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mrbean355.witnesswords.core.LetterInput
import com.github.mrbean355.witnesswords.core.WordItem
import com.github.mrbean355.witnesswords.definition.DefinitionActivity
import com.github.mrbean355.witnesswords.theme.WitnessWordsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WitnessWordsTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) }
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {
    val letters by viewModel.letters.collectAsState()
    val loading by viewModel.loading.collectAsState(false)
    val resultSummary by viewModel.resultCount.collectAsState()
    val showButtonVisible by viewModel.showButtonVisible.collectAsState(false)
    val results by viewModel.publishedResults.collectAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = viewModel::onRandomClicked,
            ) {
                Text(text = stringResource(R.string.action_random))
            }
            OutlinedButton(
                onClick = viewModel::onClearClicked,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(text = stringResource(R.string.action_clear))
            }
        }
        LetterInput(
            letters = letters,
            onLettersChange = viewModel::onLettersChanged,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        Text(
            text = resultSummary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (showButtonVisible) {
            Button(
                onClick = viewModel::onShowClicked,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = stringResource(R.string.action_show))
            }
        }
        val context = LocalContext.current
        LazyColumn {
            items(results) { word ->
                WordItem(
                    text = word,
                    onClick = { context.startActivity(DefinitionActivity(context, word)) }
                )
                Divider()
            }
        }
    }
}