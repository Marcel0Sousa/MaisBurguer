package com.marcelo.maisburguer.validation

import com.marcelo.maisburguer.R

class DocumentValidator{
    fun isValidDocument(
        document: String,
        pattern: String,
    ): TextString? {

        return when {
            document.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            document.length != pattern.length -> RawString("CPF inválido")
            else -> null
        }
    }
}