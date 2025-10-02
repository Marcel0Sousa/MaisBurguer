package com.marcelo.maisburguer.view.loginScreenView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelo.maisburguer.R
import com.marcelo.maisburguer.compose.component.CustomButton
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme
import com.marcelo.maisburguer.compose.component.CustomTextField
import com.marcelo.maisburguer.compose.component.CustomTextTitle

@Composable
fun LoginScreenView() {
    val scrollState = rememberScrollState()
    var passwordHidden by remember { mutableStateOf(true) }

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextTitle(stringResource(R.string.label_login))

            CustomTextField(
                value = "marcelo@email.com",
                label = R.string.label_email,
                placeholder = R.string.hint_email,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ) {

            }

            CustomTextField(
                obfuscate = passwordHidden,
                value = "123",
                label = R.string.label_password,
                placeholder = R.string.hint_password,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
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
            ) {

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {

                    })

                Text(stringResource(R.string.remember_password))
            }


            CustomButton(
                text = stringResource(R.string.label_login),
                enabled = true
            ) {

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.have_account))
                TextButton(onClick = {}) {
                    Text(stringResource(R.string.sign_up))
                }
            }

        }
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.BottomCenter,
        painter = painterResource(R.drawable.cover3),
        contentDescription = stringResource(R.string.hamburguer)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LightLoginScreenViewPreview() {
    MaisBurguerTheme(dynamicColor = false) {
        LoginScreenView()
    }
}