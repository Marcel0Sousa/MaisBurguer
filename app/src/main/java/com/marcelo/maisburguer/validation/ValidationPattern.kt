package com.marcelo.maisburguer.validation

fun Mask(pattern: String, currentValue: String, newValue: String): String {

    val newString = newValue.filter { it.isLetterOrDigit() }

    var newCharacter = ""
    var index = 0
    for (character in pattern) {
        if (character != '#') {
            newCharacter += character

            if (currentValue > newValue && newCharacter.length >= newValue.length) {
                newCharacter = newCharacter.dropLast(1)
            }
            continue
        }

        if (index >= newString.length) break
        newCharacter += newString[index]
        index += 1
    }

    return newCharacter
}