package com.marcelo.maisburguer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.marcelo.maisburguer.nav.MaisBurguerApp
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaisBurguerTheme(dynamicColor = false) {
                MaisBurguerApp()
            }
        }
    }
}
