package com.github.mrbean355.witnesswords

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.go
import kotlinx.android.synthetic.main.activity_main.letter_input
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.result_count
import kotlinx.android.synthetic.main.activity_main.results
import kotlinx.android.synthetic.main.activity_main.show

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
            result_count.text = resources.getQuantityString(R.plurals.result_count, it.size, it.size)
            adapter.submitList(it)
        }

        go.setOnClickListener {
            getSystemService<InputMethodManager>()
                ?.hideSoftInputFromWindow(it.windowToken, 0)

            val letters = letter_input.getLetters()
            if (letters.length == 9) {
                toggleResults(visible = false)
                viewModel.onGoClicked(letters)
            } else {
                Snackbar.make(it, R.string.error_missing_letters, Snackbar.LENGTH_LONG).show()
            }
        }
        show.setOnClickListener {
            toggleResults(visible = true)
        }
    }

    private fun toggleResults(visible: Boolean) {
        results.isVisible = visible
        show.isVisible = !visible
    }
}

@Suppress("FunctionName")
fun MainActivity(context: Context): Intent {
    return Intent(context, MainActivity::class.java)
}
