package com.marcelo.maisburguer.view.loginScreenView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelo.maisburguer.R
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme
import com.marcelo.maisburguer.util.CustomTextField

@Composable
fun LoginScreenView() {
    val scrollState = rememberScrollState()
    var passwordHidden by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            modifier = Modifier.padding(horizontal = 20.dp),
            value = "TODO",
            label = R.string.email,
            placeholder = R.string.hint_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ) {

        }

        Spacer(modifier = Modifier.padding(top = 12.dp))

        CustomTextField(
            modifier = Modifier.padding(horizontal = 20.dp),
            obfuscate = passwordHidden,
            value = "TODO",
            label = R.string.password,
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

        Spacer(modifier = Modifier.padding(top = 12.dp))

        Button(
            enabled = true,
            onClick = {

            }
        ) {
            Text(text = stringResource(R.string.send))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenViewPreview() {
    MaisBurguerTheme {
        LoginScreenView()
    }
}