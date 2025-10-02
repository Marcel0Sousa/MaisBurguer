package com.marcelo.maisburguer.compose.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelo.maisburguer.R
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

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

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    MaisBurguerTheme {
        CustomTextField(
            modifier = Modifier.padding(horizontal = 20.dp),
            value = "TODO",
            label = R.string.label_email,
            placeholder = R.string.hint_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ) {

        }
    }
}