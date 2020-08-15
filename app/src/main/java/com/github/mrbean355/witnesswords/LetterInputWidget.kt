package com.github.mrbean355.witnesswords

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.widget_letter_input.view.*

class LetterInputWidget @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.widget_letter_input, this)

        secret_input.doAfterTextChanged {
            updateFields()
        }
    }

    fun getLetters(): String {
        return secret_input.text
                .filter { it.isLetter() }
                .take(9)
                .toString()
    }

    private fun updateFields() {
        val letters = getLetters()

        letter_1.text = letters.getOrNull(0)?.toString().orEmpty()
        letter_2.text = letters.getOrNull(1)?.toString().orEmpty()
        letter_3.text = letters.getOrNull(2)?.toString().orEmpty()
        letter_4.text = letters.getOrNull(3)?.toString().orEmpty()
        letter_5.text = letters.getOrNull(4)?.toString().orEmpty()
        letter_6.text = letters.getOrNull(5)?.toString().orEmpty()
        letter_7.text = letters.getOrNull(6)?.toString().orEmpty()
        letter_8.text = letters.getOrNull(7)?.toString().orEmpty()
        letter_9.text = letters.getOrNull(8)?.toString().orEmpty()
    }
}