package com.github.mrbean355.witnesswords

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.github.mrbean355.witnesswords.databinding.WidgetLetterInputBinding

class LetterInputWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: WidgetLetterInputBinding = WidgetLetterInputBinding.inflate(LayoutInflater.from(context), this, true)
    private val inputs: List<TextView>
    var onTextInput: (String) -> Unit = {}

    init {
        with(binding) {
            inputs = listOf(letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8, letter9)
        }
        binding.secretInput.doAfterTextChanged {
            updateFields()
        }
        updateFields()
    }

    private fun getLetters(): String {
        return binding.secretInput.text
            .filter { it.isLetter() }
            .take(LETTER_COUNT)
            .toString()
            .lowercase()
    }

    private fun updateFields() {
        val letters = getLetters()
        val cursorPos = letters.length
        inputs.forEachIndexed { index, textView ->
            if (index == cursorPos) {
                textView.text = "_"
            } else {
                textView.text = letters.getOrNull(index)?.toString().orEmpty()
            }
        }
        onTextInput(letters)
    }
}