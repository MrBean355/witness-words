package com.github.mrbean355.witnesswords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val ready = MutableLiveData<Boolean>(true)
    val results = MutableLiveData<List<String>>()

    fun onGoClicked(input: String) {
        ready.value = false
        viewModelScope.launch {
            results.value = searchWords(input)
            ready.value = true
        }
    }
}