package com.github.mrbean355.witnesswords

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = WordAdapter {
            startActivity(DefinitionActivity(this, it))
        }
        results.adapter = adapter
        results.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.ready.observe(this) {
            progress_bar.isVisible = !it
            go.isEnabled = it
        }
        viewModel.results.observe(this) {
            adapter.submitList(it)
        }

        go.setOnClickListener {
            val letters = letter_input.getLetters()
            if (letters.length == 9) {
                viewModel.onGoClicked(letters)
            } else {
                Snackbar.make(it, R.string.error_missing_letters, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}

@Suppress("FunctionName")
fun MainActivity(context: Context): Intent {
    return Intent(context, MainActivity::class.java)
}
