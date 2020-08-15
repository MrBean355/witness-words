package com.github.mrbean355.witnesswords

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.sf.extjwnl.data.POS
import net.sf.extjwnl.dictionary.Dictionary

private val words: Collection<String> by lazy {
    val dictionary = Dictionary.getDefaultResourceInstance()
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
            .filter { word ->
                val wc = word.countChars()
                for (entry in wc) {
                    if (entry.value > counts.getValue(entry.key)) {
                        return@filter false
                    }
                }
                true
            }
            .filter { it.contains(input.first()) }
            .sorted()
            .toList()
}

private fun String.countChars(): Map<Char, Int> {
    return associateWith { ch -> count { ch == it } }
}
