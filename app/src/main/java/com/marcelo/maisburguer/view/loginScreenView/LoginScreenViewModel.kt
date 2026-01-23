package com.marcelo.maisburguer.view.loginScreenView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelo.maisburguer.model.LoginUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _Email = MutableStateFlow("")
    var email: StateFlow<String> = _Email.asStateFlow()
        private set

    private val _Password = MutableStateFlow("")
    var password: StateFlow<String> = _Password.asStateFlow()
        private set

    fun send() {

        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            delay(3000)
            _uiState.update {
                it.copy(isLoading = false)
            }

            /*_uiState.update {
                it.copy(isLoading = false, messageErro = ("Usuário não encontrado"))
            }*/
        }


    }

    fun reset() {
        _uiState.update { LoginUiState() }
    }
}