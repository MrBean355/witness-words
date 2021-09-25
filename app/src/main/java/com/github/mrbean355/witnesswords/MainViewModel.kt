package com.github.mrbean355.witnesswords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var results = listOf<String>()

    val loading = MutableLiveData(false)
    val showButtonVisible = MutableLiveData(false)
    val resultCount = MutableLiveData<Int?>(null)
    val publishedResults = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch {
            loadWords()
        }
    }

    fun onLettersChanged(letters: String) {
        if (letters.length >= LETTER_COUNT) {
            findWords(letters)
        } else {
            showButtonVisible.value = false
            resultCount.value = null
            publishedResults.value = emptyList()
        }
    }

    fun onShowClicked() {
        showButtonVisible.value = false
        publishedResults.value = results
    }

    private fun findWords(letters: String) {
        loading.value = true
        viewModelScope.launch {
            showButtonVisible.value = true
            searchWords(letters).let {
                results = it
                resultCount.value = it.size
            }
            loading.value = false
        }
    }
}