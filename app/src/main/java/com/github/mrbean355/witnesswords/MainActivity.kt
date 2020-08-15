package com.github.mrbean355.witnesswords

import android.os.Bundle
import android.widget.EditText
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

        viewModel.initialise()
        viewModel.ready.observe(this) {
            progress_bar.isVisible = !it
            go.isEnabled = it
        }
        viewModel.results.observe(this) {
            adapter.submitList(it)
        }

        go.setOnClickListener {
            val letters = getLetters()
            if (letters.length == 9) {
                viewModel.onGoClicked(getLetters())
            } else {
                Snackbar.make(it, "Please enter all 9 letters", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun getLetters(): String {
        return letter_1.firstOrEmpty() +
                letter_2.firstOrEmpty() +
                letter_3.firstOrEmpty() +
                letter_4.firstOrEmpty() +
                letter_5.firstOrEmpty() +
                letter_6.firstOrEmpty() +
                letter_7.firstOrEmpty() +
                letter_8.firstOrEmpty() +
                letter_9.firstOrEmpty()
    }

    private fun EditText.firstOrEmpty(): String {
        return text.toString().firstOrNull()?.toLowerCase()?.toString() ?: ""
    }
}