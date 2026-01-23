package com.marcelo.maisburguer.validation

import com.marcelo.maisburguer.R

class PasswordValidator{
    fun isValidInputPassword(
        fieldPassword: String,
        fieldConfirmPassword: String
    ): TextString? {
        return when {
            fieldPassword.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            fieldPassword.length < 8 -> RawString("Campo deve conter 8 ou mais caracteres")
            fieldPassword != fieldConfirmPassword -> RawString("As senhas não coincidem")
            else -> null
        }
    }
}