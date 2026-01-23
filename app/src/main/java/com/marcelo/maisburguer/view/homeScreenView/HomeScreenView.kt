package com.marcelo.maisburguer.view.homeScreenView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme

@Composable
fun HomeScreenView(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {}
        ) { paddingValues ->
            HomeScreenContent(modifier.padding(top = paddingValues.calculateTopPadding()))
        }
    }
}

@Composable
private fun HomeScreenContent(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Text("Home Screen")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MaisBurguerTheme(dynamicColor = false) {
        HomeScreenView()
    }
}