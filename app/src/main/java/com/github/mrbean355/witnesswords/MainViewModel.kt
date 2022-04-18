package com.github.mrbean355.witnesswords

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.github.mrbean355.witnesswords.core.LETTER_COUNT
import com.github.mrbean355.witnesswords.core.getRandomWord
import com.github.mrbean355.witnesswords.core.loadWords
import com.github.mrbean355.witnesswords.core.searchWords
import com.github.mrbean355.witnesswords.core.shuffled
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var results = listOf<String>()
    private val _letters = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _showButtonVisible = MutableStateFlow(false)
    private val _resultCount = MutableStateFlow("")
    private val _publishedResults = MutableStateFlow<List<String>>(emptyList())

    val letters: StateFlow<String> = _letters.asStateFlow()
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    val showButtonVisible: StateFlow<Boolean> = _showButtonVisible.asStateFlow()
    val resultCount: StateFlow<String> = _resultCount.asStateFlow()
    val publishedResults: StateFlow<List<String>> = _publishedResults.asStateFlow()

    init {
        viewModelScope.launch {
            loadWords()
        }
    }

    fun onLettersChanged(newLetters: String) {
        _letters.value = newLetters
        if (newLetters.length >= LETTER_COUNT) {
            findWords(newLetters.lowercase())
        } else {
            reset()
        }
    }

    fun onShowClicked() {
        _showButtonVisible.value = false
        _publishedResults.value = results
    }

    private fun findWords(letters: String) {
        _loading.value = true
        viewModelScope.launch {
            searchWords(letters).let {
                _showButtonVisible.value = it.isNotEmpty()
                results = it
                _resultCount.value = getApplication<Application>().resources
                    .getQuantityString(R.plurals.result_count, it.size, it.size)
            }
            _loading.value = false
        }
    }

    fun onRandomClicked() {
        viewModelScope.launch {
            _loading.value = true
            reset()
            onLettersChanged(getRandomWord().uppercase().shuffled())
        }
    }

    fun onClearClicked() {
        _letters.value = ""
        reset()
    }

    private fun reset() {
        _showButtonVisible.value = false
        _resultCount.value = ""
        _publishedResults.value = emptyList()
    }
}