package com.marcelo.maisburguer.validation

import com.marcelo.maisburguer.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BirthdayValidator{
    fun isValidBirthday(
        birthday: String,
        pattern: String,
    ): TextString? {

        return when {
            birthday.isBlank() -> ResiurceString(R.string.label_campo_obrigatorio)
            birthday.length != pattern.length -> ResiurceString(R.string.label_data_de_nascimento_invalida)
            else -> {
                try {

                    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    simpleDateFormat.isLenient = false

                    val birthDate: Date = simpleDateFormat.parse(birthday)
                        ?: return ResiurceString(R.string.label_data_de_nascimento_invalida)

                    if (birthDate.after(Date())) {
                        RawString("A data não pode ser futura")
                    } else {
                        null
                    }
                } catch (e: ParseException) {
                    ResiurceString(R.string.label_data_de_nascimento_invalida)
                }
            }
        }

    }
}