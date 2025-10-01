package com.marcelo.maisburguer.util

import android.R.attr.label
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    obfuscate: Boolean = false,
    value: String,
    @StringRes label: Int,
    @StringRes placeholder: Int,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        label = {
            Text(text = stringResource(label))
        },
        placeholder = {
            Text(text = stringResource(placeholder))
        },
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        onValueChange = onValueChange,
        visualTransformation = if (obfuscate) PasswordVisualTransformation() else VisualTransformation.None


    )

}