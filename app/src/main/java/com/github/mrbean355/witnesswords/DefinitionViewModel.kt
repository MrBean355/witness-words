package com.github.mrbean355.witnesswords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DefinitionViewModel : ViewModel() {
    val definitions = MutableLiveData<List<Definition>>()

    fun initialise(word: String) {
        viewModelScope.launch {
            definitions.value = getDefinitions(word)
        }
    }
}