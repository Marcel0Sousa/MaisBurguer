package com.marcelo.maisburguer.compose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

@Composable
fun CustomTextTitle(text: String) {
    Text(
        text = text,
        fontSize = 34.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Left,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 20.dp)
        )
}

@Preview(showBackground = true)
@Composable
private fun CustomTextTitlePreview() {
    MaisBurguerTheme {
        CustomTextTitle("Login")
    }
}