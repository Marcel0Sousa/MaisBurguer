package com.marcelo.maisburguer.model

data class SignUpUiState(
    val isLoading: Boolean = false,
    val goToHome: Boolean = false,
    val messageErro: String? = null,
    val email: String ="",
    val password: String =""
)
