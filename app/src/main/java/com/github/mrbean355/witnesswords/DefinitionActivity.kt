package com.github.mrbean355.witnesswords

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import kotlinx.android.synthetic.main.activity_definition.*

private const val KEY_WORD = "WORD"

class DefinitionActivity : AppCompatActivity(R.layout.activity_definition) {
    private val viewModel by viewModels<DefinitionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        val word = intent.getStringExtra(KEY_WORD).orEmpty()
        word_heading.text = word

        val adapter = DefinitionAdapter()
        definitions.adapter = adapter
        definitions.addItemDecoration(DividerItemDecoration(this, VERTICAL))

        viewModel.initialise(word)
        viewModel.definitions.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

@Suppress("FunctionName")
fun DefinitionActivity(context: Context, word: String): Intent {
    return Intent(context, DefinitionActivity::class.java)
            .putExtra(KEY_WORD, word)
}
