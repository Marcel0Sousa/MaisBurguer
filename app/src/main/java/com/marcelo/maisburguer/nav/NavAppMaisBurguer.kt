package com.marcelo.maisburguer.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcelo.maisburguer.ui.theme.MaisBurguerTheme
import com.marcelo.maisburguer.util.Routes
import com.marcelo.maisburguer.view.loginScreenView.LoginScreenView

@Composable
fun MaisBurguerApp() {
    val navController = rememberNavController()
    MaisBurguerNavHost(navController)
}

@Composable
fun MaisBurguerNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.LOGIN.route
    ) {
        composable(Routes.LOGIN.route) {
            LoginScreenView()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MaisBurguerAppPreview() {
    MaisBurguerTheme {
        MaisBurguerApp()
    }
}