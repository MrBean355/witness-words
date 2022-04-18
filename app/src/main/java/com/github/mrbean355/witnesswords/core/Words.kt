package com.github.mrbean355.witnesswords.core

import com.github.mrbean355.witnesswords.definition.Definition
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.sf.extjwnl.data.POS
import net.sf.extjwnl.dictionary.Dictionary

const val LETTER_COUNT = 9

val dictionary: Dictionary by lazy {
    Dictionary.getDefaultResourceInstance()
}

private val words: Collection<String> by lazy {
    POS.getAllPOS()
        .flatMap { dictionary.getIndexWordIterator(it).asSequence().toList() }
        .map { it.lemma }
        .distinct()
}

suspend fun loadWords(): Unit = withContext(IO) {
    words
    Unit
}

suspend fun searchWords(input: String): List<String> = withContext(IO) {
    val counts = input.countChars()
    words.asSequence()
        .filter { it.length >= 4 }
        .filter { word -> word.all { it in input } }
        .filter { it.contains(input.first()) }
        .filter { word ->
            val wc = word.countChars()
            for (entry in wc) {
                if (entry.value > counts.getValue(entry.key)) {
                    return@filter false
                }
            }
            true
        }
        .sorted()
        .toList()
}

suspend fun getRandomWord(): String = withContext(IO) {
    var word: String
    do word = dictionary.getRandomIndexWord(POS.NOUN).lemma
    while (word.length != LETTER_COUNT || !word.all(Char::isLetter))
    word
}

suspend fun getDefinitions(word: String): List<Definition> = withContext(IO) {
    dictionary.lookupAllIndexWords(word).indexWordArray.map { indexWord ->
        Definition(
            type = indexWord.pos.label,
            detail = indexWord.senses.joinToString("\n\n") { it.gloss }
        )
    }
}
