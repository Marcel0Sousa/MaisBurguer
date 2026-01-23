package com.marcelo.maisburguer.validation

import android.util.Patterns
import com.marcelo.maisburguer.R

class EmailValidator {

    // function extension
    private fun String.isPatternsValidEmail(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }


    fun isValidEmail(email: String): TextString? {
        return when {
            email.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            !email.isPatternsValidEmail() -> RawString("Informe um email válido")
            else -> null
        }
    }
}