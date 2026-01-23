package com.marcelo.maisburguer.model

data class LoginUiState(
    val isLoading: Boolean = false,
    val goToHome: Boolean = false,
    val messageErro: String? = null,
    val email: String ="",
    val password: String ="",
    val repeatPassword: String =""
)
