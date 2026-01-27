package com.marcelo.maisburguer.test.validation

import com.marcelo.maisburguer.R
import com.marcelo.maisburguer.validation.BirthdayValidator
import com.marcelo.maisburguer.validation.RawString
import com.marcelo.maisburguer.validation.ResiurceString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BirthdayValidatorTest {

    private lateinit var validator: BirthdayValidator

    @Before
    fun setup() {
        validator = BirthdayValidator()
    }

    @Test
    fun `isValidBirthday returns error for blank birthday`() {
        val result = validator.isValidBirthday("", "dd/MM/yyyy")
        assertEquals(ResiurceString(R.string.label_campo_obrigatorio), result)
    }

    @Test
    fun `isValidBirthday returns error for invalid length`() {
        val result = validator.isValidBirthday("01/01/2", "dd/MM/yyyy")
        assertEquals(ResiurceString(R.string.label_data_de_nascimento_invalida), result)
    }

    @Test
    fun `isValidBirthday returns error for future date`() {
        val futureDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(System.currentTimeMillis() + 86400000)) // Amanhã
        val result = validator.isValidBirthday(futureDate, "dd/MM/yyyy")
        assertEquals(RawString("A data não pode ser futura"), result)
    }

    @Test
    fun `isValidBirthday returns error for invalid date format`() {
        val result = validator.isValidBirthday("32/01/2000", "dd/MM/yyyy")
        assertEquals(ResiurceString(R.string.label_data_de_nascimento_invalida), result)
    }

    @Test
    fun `isValidBirthday returns null for valid past date`() {
        val result = validator.isValidBirthday("01/01/2000", "dd/MM/yyyy")
        assertNull(result)
    }

    @Test
    fun `isValidBirthday returns null for valid current date`() {
        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val result = validator.isValidBirthday(today, "dd/MM/yyyy")
        assertNull(result)
    }
}