package com.marcelo.maisburguer.validation

import com.marcelo.maisburguer.R

class ConfirmPasswordValidator{
    fun isValidConfirmPassword(
        fieldConfirmPassword: String,
        fieldPassword: String,
    ): TextString? {
        return when {
            fieldConfirmPassword.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            fieldConfirmPassword.length < 8 -> RawString("Campo deve conter 8 ou mais caracteres")
            fieldConfirmPassword != fieldPassword -> RawString("As senhas não coincidem")
            else -> null
        }
    }
}