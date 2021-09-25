package com.github.mrbean355.witnesswords.definition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.github.mrbean355.witnesswords.databinding.ActivityDefinitionBinding

private const val KEY_WORD = "WORD"

class DefinitionActivity : AppCompatActivity() {
    private val viewModel by viewModels<DefinitionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDefinitionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        val word = intent.getStringExtra(KEY_WORD).orEmpty()
        binding.wordHeading.text = word

        val adapter = DefinitionAdapter()
        binding.definitions.adapter = adapter
        binding.definitions.addItemDecoration(DividerItemDecoration(this, VERTICAL))

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
