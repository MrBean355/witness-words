package com.github.mrbean355.witnesswords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.mrbean355.witnesswords.core.LETTER_COUNT
import com.github.mrbean355.witnesswords.core.loadWords
import com.github.mrbean355.witnesswords.core.searchWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var results = listOf<String>()
    private val _loading = MutableStateFlow(false)
    private val _showButtonVisible = MutableStateFlow(false)
    private val _resultCount = MutableStateFlow("")
    private val _publishedResults = MutableStateFlow<List<String>>(emptyList())

    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    val showButtonVisible: StateFlow<Boolean> = _showButtonVisible.asStateFlow()
    val resultCount: StateFlow<String> = _resultCount.asStateFlow()
    val publishedResults: StateFlow<List<String>> = _publishedResults.asStateFlow()

    init {
        viewModelScope.launch {
            loadWords()
        }
    }

    fun onLettersChanged(letters: String) {
        if (letters.length >= LETTER_COUNT) {
            findWords(letters)
        } else {
            _showButtonVisible.value = false
            _resultCount.value = ""
            _publishedResults.value = emptyList()
        }
    }

    fun onShowClicked() {
        _showButtonVisible.value = false
        _publishedResults.value = results
    }

    private fun findWords(letters: String) {
        _loading.value = true
        viewModelScope.launch {
            _showButtonVisible.value = true
            searchWords(letters).let {
                results = it
                _resultCount.value = getApplication<Application>().resources
                    .getQuantityString(R.plurals.result_count, it.size, it.size)
            }
            _loading.value = false
        }
    }
}