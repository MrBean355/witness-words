package com.github.mrbean355.witnesswords.core

fun String.shuffled(): String {
    return toList().shuffled().joinToString(separator = "")
}

fun String.countChars(): Map<Char, Int> {
    return associateWith { ch -> count { ch == it } }
}