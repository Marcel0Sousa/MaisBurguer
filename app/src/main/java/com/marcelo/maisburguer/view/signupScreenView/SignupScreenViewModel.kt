package com.marcelo.maisburguer.view.signupScreenView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelo.maisburguer.api.MaisBurguerService
import com.marcelo.maisburguer.data.MaisBurguerRepository
import com.marcelo.maisburguer.data.UserCreateResponse
import com.marcelo.maisburguer.data.UserRequest
import com.marcelo.maisburguer.model.FieldState
import com.marcelo.maisburguer.model.FormState
import com.marcelo.maisburguer.model.SignUpUiState
import com.marcelo.maisburguer.validation.BirthdayValidator
import com.marcelo.maisburguer.validation.ConfirmPasswordValidator
import com.marcelo.maisburguer.validation.DocumentValidator
import com.marcelo.maisburguer.validation.EmailValidator
import com.marcelo.maisburguer.validation.InputValidator
import com.marcelo.maisburguer.validation.Mask
import com.marcelo.maisburguer.validation.PasswordValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class SignupScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(FormState())
    var formState: StateFlow<FormState> = _formState.asStateFlow()

    private val emailValidator = EmailValidator()
    private val inputValidator = InputValidator()
    private val passwordValidator = PasswordValidator()
    private val confirmPasswordValidator = ConfirmPasswordValidator()
    private val documentValidator = DocumentValidator()
    private val birthdayValidator = BirthdayValidator()

    fun changeName(newName: String) {
        val nameErro = inputValidator.isValidInput(newName)

        _formState.update { formState ->
            formState.copy(
                name = FieldState(
                    field = newName,
                    error = inputValidator.isValidInput(newName),
                    isValid = nameErro == null
                )
            )
        }
        updateButton()
    }

    fun changeLastname(newLastname: String) {
        val lastnameErro = inputValidator.isValidInput(newLastname)

        _formState.update { formState ->
            formState.copy(
                lastname = FieldState(
                    field = newLastname,
                    error = inputValidator.isValidInput(newLastname),
                    isValid = lastnameErro == null
                )
            )
        }
        updateButton()
    }

    fun changeEmail(newEmail: String) {
        val emailErro = emailValidator.isValidEmail(newEmail)

        _formState.update { formState ->
            formState.copy(
                email = FieldState(
                    field = newEmail,
                    error = emailValidator.isValidEmail(email = newEmail),
                    isValid = emailErro == null
                )
            )
        }
        updateButton()
    }

    fun changePassword(newPassword: String) {
        val passwordError = passwordValidator.isValidInputPassword(
            fieldPassword = newPassword,
            fieldConfirmPassword = formState.value.confirmPassword.field
        )

        _formState.update { formState ->
            formState.copy(
                password = FieldState(
                    field = newPassword,
                    error = passwordError,
                    isValid = passwordError == null
                )
            )
        }
        revalidateConfirmPassword()
        updateButton()
    }

    fun changeConfirmPassword(confirmPassword: String) {
        val confirmPasswordError = confirmPasswordValidator
            .isValidConfirmPassword(
                fieldConfirmPassword = confirmPassword,
                fieldPassword = formState.value.password.field
            )

        _formState.update { formState ->
            formState.copy(
                confirmPassword = FieldState(
                    field = confirmPassword,
                    error = confirmPasswordError,
                    isValid = confirmPasswordError == null
                )
            )
        }
        revalidatePassword()
        updateButton()
    }

    fun changeDocument(newDocument: String) {
        val pattern = "###.###.###-##"
        val currenteDocument = formState.value.document.field
        val result =
            Mask(pattern = pattern, currentValue = currenteDocument, newValue = newDocument)

        val documentErro = documentValidator.isValidDocument(
            document = result,
            pattern = pattern
        )
        _formState.update { formState ->
            formState.copy(
                document = FieldState(
                    field = result,
                    error = documentValidator.isValidDocument(document = result, pattern = pattern),
                    isValid = documentErro == null
                )
            )
        }
        updateButton()
    }

    fun changeBirthday(newBirthday: String) {
        val pattern = "##/##/####"
        val currenteBrithday = formState.value.birthday.field
        val result =
            Mask(pattern = pattern, currentValue = currenteBrithday, newValue = newBirthday)

        val birthdayErro = birthdayValidator.isValidBirthday(
            birthday = result,
            pattern = pattern
        )
        _formState.update { formState ->
            formState.copy(
                birthday = FieldState(
                    field = result,
                    error = birthdayValidator.isValidBirthday(birthday = result, pattern = pattern),
                    isValid = birthdayErro == null
                )
            )
        }
        updateButton()

    }

    private fun revalidatePassword() {
        val currentPassword = _formState.value.password.field
        val currentConfirmePassword = _formState.value.confirmPassword.field
        val passwordError = passwordValidator.isValidInputPassword(
            fieldPassword = currentPassword,
            fieldConfirmPassword = currentConfirmePassword
        )

        _formState.update { formState ->
            formState.copy(
                password = formState.password.copy(
                    error = passwordError,
                    isValid = passwordError == null
                )
            )
        }
    }

    private fun revalidateConfirmPassword() {
        val currentPassword = _formState.value.password.field
        val currentConfirmPassword = _formState.value.confirmPassword.field
        val confirmPasswordError = confirmPasswordValidator.isValidConfirmPassword(
            fieldConfirmPassword = currentConfirmPassword,
            fieldPassword = currentPassword
        )
        _formState.update { formState ->
            formState.copy(
                confirmPassword = formState.confirmPassword.copy(
                    error = confirmPasswordError,
                    isValid = confirmPasswordError == null
                )
            )
        }
    }

    private fun updateButton() {

        val formIsValid = with(_formState.value) {
            name.isValid &&
                    lastname.isValid &&
                    email.isValid &&
                    password.isValid &&
                    confirmPassword.isValid &&
                    birthday.isValid &&
                    document.isValid

        }

        _formState.update { currentState ->
            currentState.copy(
                formIsValid = formIsValid
            )
        }
    }

    fun send() {

        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {

            with(_formState.value) {
                val parseDate =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(birthday.field)

                val dateFormatted =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(parseDate!!)

                val userRequest = UserRequest(
                    name = name.field,
                    email = email.field,
                    password = password.field,
                    document = document.field,
                    birthday = dateFormatted
                )

                val service = MaisBurguerService.create()
                val repository = MaisBurguerRepository(service)
                val result = repository.postUser(userRequest)

                Log.i("Test", "Content is $result")

                when (result) {
                    is UserCreateResponse.Success -> {
                        _uiState.update {
                            it.copy(isLoading = false, goToHome = true)
                        }
                    }

                    is UserCreateResponse.ErrorAuth -> {
                        _uiState.update {
                            it.copy(isLoading = false, messageErro = result.detail.message)
                        }
                    }

                    is UserCreateResponse.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, messageErro = result.detail)
                        }
                    }
                }
            }
        }


    }

    fun reset() {
        _uiState.update { SignUpUiState() }
    }
}