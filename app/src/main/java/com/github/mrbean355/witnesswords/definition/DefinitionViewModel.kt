package com.github.mrbean355.witnesswords.definition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mrbean355.witnesswords.core.getDefinitions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DefinitionViewModel : ViewModel() {
    private val _definitions = MutableStateFlow<List<Definition>>(emptyList())
    val definitions: StateFlow<List<Definition>> = _definitions.asStateFlow()

    fun initialise(word: String) {
        viewModelScope.launch {
            _definitions.value = getDefinitions(word)
        }
    }
}