package com.marcelo.maisburguer.model

import com.marcelo.maisburguer.validation.TextString


data class FieldState(
    val field: String = "",
    val error: TextString? = null,
    val isValid: Boolean = false
)

data class FormState (
    val name: FieldState = FieldState(),
    val lastname: FieldState = FieldState(),
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState(),
    val confirmPassword: FieldState = FieldState(),
    val document: FieldState = FieldState(),
    val birthday: FieldState = FieldState(),
    val formIsValid: Boolean = false
)