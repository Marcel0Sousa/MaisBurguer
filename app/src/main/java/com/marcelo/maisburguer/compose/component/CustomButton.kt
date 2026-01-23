package com.marcelo.maisburguer.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

@Composable
fun CustomButton(
    text: String,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit,

    ) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            onClick = onClick,
            enabled = enabled && !loading,
            shape = RoundedCornerShape(10.dp)
        ) {
            if (!loading) {
                Text(text.uppercase())
            }
        }

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    MaisBurguerTheme {
        Column {
            CustomButton("Login", enabled = false) { }
            CustomButton("Login", enabled = true) { }
            CustomButton("Login", loading = true) { }
            CustomButton("Login", loading = false) { }
        }
    }
}