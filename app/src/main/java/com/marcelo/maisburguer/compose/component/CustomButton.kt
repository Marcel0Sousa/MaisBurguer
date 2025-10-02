package com.marcelo.maisburguer.compose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

@Composable
fun CustomButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,

    ) {

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text.uppercase())
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    MaisBurguerTheme {
        CustomButton("Login") { }
    }
}