package com.github.mrbean355.witnesswords

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.mrbean355.witnesswords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.letterInput.onTextInput = viewModel::onLettersChanged
        binding.showButton.setOnClickListener {
            viewModel.onShowClicked()
        }

        val adapter = WordAdapter {
            startActivity(DefinitionActivity(this, it))
        }
        binding.resultsList.adapter = adapter
        binding.resultsList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.loading.observe(this) {
            binding.progressIndicator.isInvisible = !it
        }
        viewModel.showButtonVisible.observe(this) {
            binding.showButton.isInvisible = !it
        }
        viewModel.resultCount.observe(this) { count ->
            if (count != null) {
                binding.resultCount.text = resources.getQuantityString(R.plurals.result_count, count, count)
                hideKeyboard(binding.root)
            } else {
                binding.resultCount.text = ""
            }
        }
        viewModel.publishedResults.observe(this) { results ->
            adapter.submitList(results)
        }
    }

    private fun hideKeyboard(view: View) {
        getSystemService<InputMethodManager>()
            ?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}