package com.marcelo.maisburguer.view.signupScreenView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marcelo.maisburguer.R
import com.marcelo.maisburguer.compose.component.CustomAlert
import com.marcelo.maisburguer.compose.component.CustomButton
import com.marcelo.maisburguer.compose.component.CustomTextField
import com.marcelo.maisburguer.compose.component.CustomTextTitle
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreenView(
    onBackLoginScreen: () -> Unit,
    onNavigateToHome: () -> Unit,
    SignupViewModel: SignupScreenViewModel = viewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.label_login))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onBackLoginScreen()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { paddingValues ->
            SignupContentScreen(
                SignupViewModel = SignupViewModel,
                onNavigateToHome = onNavigateToHome,
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
            )
        }
    }
}

@Composable
private fun SignupContentScreen(
    SignupViewModel: SignupScreenViewModel = viewModel(),
    onNavigateToHome: () -> Unit,
    modifier: Modifier,
) {
    val uiState by SignupViewModel.uiState.collectAsState()
    val formState by SignupViewModel.formState.collectAsState()

    val scrollState = rememberScrollState()
    var passwordHidden by remember { mutableStateOf(true) }

    var confirmPasswordHidden by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .navigationBarsPadding()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LaunchedEffect(
            key1 = uiState.goToHome
        ) {
            if (uiState.goToHome) {
                onNavigateToHome()
                SignupViewModel.reset()
            }
        }

        uiState.messageErro?.let {
            CustomAlert(
                onDismissRequest = {},
                onConfirmation = { SignupViewModel.reset() },
                dialogTitle = stringResource(R.string.app_name),
                dialogText = it,
                icon = Icons.Filled.Info
            )
        }

        CustomTextTitle(stringResource(R.string.sign_up))

        CustomTextField(
            value = formState.name.field,
            label = R.string.label_name,
            placeholder = R.string.hint_name,
            error = formState.name.error?.value,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ) {name ->
            SignupViewModel.changeName(name)
        }

        CustomTextField(
            value = formState.lastname.field,
            label = R.string.label_lastname,
            placeholder = R.string.hint_lastname,
            error = formState.lastname.error?.value,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ) {lastname ->
            SignupViewModel.changeLastname(lastname)
        }

        CustomTextField(
            value = formState.email.field,
            placeholder = R.string.hint_email,
            label = R.string.label_email,
            error = formState.email.error?.value,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ) {email ->
            SignupViewModel.changeEmail(email)
        }


        CustomTextField(
            obfuscate = passwordHidden,
            value = formState.password.field,
            label = R.string.label_password,
            placeholder = R.string.hint_password,
            error = formState.password.error?.value,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordHidden = !passwordHidden
                    }
                ) {
                    val image = when (passwordHidden) {
                        true -> Icons.Filled.VisibilityOff
                        false -> Icons.Filled.Visibility
                    }

                    val description = when (passwordHidden) {
                        true -> stringResource(R.string.show_password)
                        false -> stringResource(R.string.hide_password)
                    }
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        ) {password ->
            SignupViewModel.changePassword(password)
        }

        CustomTextField(
            obfuscate = confirmPasswordHidden,
            value = formState.confirmPassword.field,
            label = R.string.label_confirm_password,
            placeholder = R.string.hint_confirm_password,
            error = formState.confirmPassword.error?.value,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            trailingIcon = {
                IconButton(
                    onClick = {
                        confirmPasswordHidden = !confirmPasswordHidden
                    }
                ) {
                    val image = when (confirmPasswordHidden) {
                        true -> Icons.Filled.VisibilityOff
                        false -> Icons.Filled.Visibility
                    }

                    val description = when (confirmPasswordHidden) {
                        true -> stringResource(R.string.show_password)
                        false -> stringResource(R.string.hide_password)
                    }
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        ) {confirmPassword ->
            SignupViewModel.changeConfirmPassword(confirmPassword)
        }

        CustomTextField(
            value = TextFieldValue(
                text = formState.document.field,
                selection = TextRange(formState.document.field.length)),
            label = R.string.label_document,
            placeholder = R.string.hint_document,
            error = formState.document.error?.value,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ) {textFieldValue ->
            SignupViewModel.changeDocument(textFieldValue.text)
        }

        CustomTextField(
            value = TextFieldValue(
                text = formState.birthday.field,
                selection = TextRange(formState.birthday.field.length)
            ),
            label = R.string.label_birth_date,
            placeholder = R.string.hint_birth_date,
            error = formState.birthday.error?.value,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ) { textFieldValue ->
            SignupViewModel.changeBirthday(textFieldValue.text)
        }

        CustomButton(
            text = stringResource(R.string.label_sign_up),
            enabled = true, //formState.formIsValid,
            loading = uiState.isLoading
        ) {
            SignupViewModel.send()
        }

        Spacer(modifier = Modifier.padding(bottom = 50.dp).scale(0.7f))

    }
}

@Preview
@Composable
private fun SignupScreenPreview() {
    MaisBurguerTheme {
        SignupScreenView({}, {})
    }

}

