package com.marcelo.maisburguer.validation

import com.marcelo.maisburguer.R

class InputValidator {
    fun isValidInput(field: String): TextString? {
        return when {
            field.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            field.length < 3 -> RawString("Campo deve conter 3 ou mais caracteres")
            else -> null
        }
    }
}